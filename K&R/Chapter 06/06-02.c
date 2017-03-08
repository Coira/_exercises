#include <stdio.h>
#include <ctype.h>
#include <string.h>
#include <stdlib.h>

#define MAXWORD 100
#define BUFSIZE 100
#define TRUE 1
#define FALSE 0

//#define NKEYS (sizeof varnames / sizeof(struct key))

char vartypes[][MAXWORD] = {"bool",
                            "char",
                            "double",
                            "float",
                            "int",
                            "long",
                            "short"};
                    
   
struct tnode {
  char *word;
  int count;
  char * simwords[MAXWORD];  //stores similar words
  struct tnode *left;
  struct tnode *right;
};

struct tnode *addtree(struct tnode *, char *, int);
void treeprint(struct tnode *);
int getword(char *, int);
int mystrcmp(const void * s, const void * t);
//int binsearch(char *, struct key *, int);

main(int argc, char *argv[])
{
  struct tnode *root;
  char word[MAXWORD];

  int foundVar = FALSE;
  root = NULL;

  int n = 6;
  if (argc > 1) {
    if (isdigit(*argv[1]))
      n = atoi(argv[1]);
  }

  while (getword(word, MAXWORD) != EOF) {
    
    // wait for var to come along
    if (!foundVar && isalpha(word[0])) { 
      
      char * found;
      found = (char*) bsearch(word, vartypes, 7, MAXWORD,
                              (int(*)(const void*, const void*))strcmp);

      if (found)
        foundVar = TRUE;
    }
    else if (foundVar) {  // add var name to tree
      foundVar = FALSE;
      root = addtree(root, word, n);
    }
  }
  
  treeprint(root);
  return 0;
}

struct tnode *talloc(void);
char *strdupx(char *);

struct tnode *addtree(struct tnode *p, char *w, int n)
{
  int cond;

  if (p == NULL) {
    // as strndup doesn't seem to work for me...
    char *temp = strdup(w);
    if (strlen(temp) > n)
      temp[n] = '\0';

    p = talloc();
    p->word = strdup(temp);
    p->count = 1;
    p->simwords[0] = strdup(w);
    p->left = p->right = NULL;
  } else {
    cond = strcmp(w, p->word);

    // similar by n chars (are we meant to include words that match exactly?) 
    if (cond == 0 || strncmp(w, p->word, n) == 0) {
      //check we don't have the word in the list already
      char ** found;
      found = (char**) bsearch(&w, &(p->simwords[0]), p->count,
                               sizeof(char *), mystrcmp);

      // add to the word list and resort
      if (!found) {
        p->simwords[p->count++] = strdup(w);
        qsort(&(p->simwords[0]), p->count, sizeof(char *),
              mystrcmp);
      }
    }
    else if (cond < 0)
      p->left = addtree(p->left, w, n);
    else if (cond > 0)
      p->right = addtree(p->right, w, n);
  }
    return p;
  
}

void treeprint(struct tnode *p)
{
  if (p != NULL) {
    treeprint(p->left);
    printf("Word root: %s\n", p->word);
    printf("Similar words: ");
    
    for (int i = 0; i < p->count; i++)
      printf("%s ", p->simwords[i]);
    printf("\n\n");
    treeprint(p->right);
  }
}

struct tnode *talloc(void)
{
  return (struct tnode *) malloc(sizeof(struct tnode));
}

char *strdupx(char *s)
{
  char *p;

  p = (char *) malloc(strlen(s) + 1);
  if (p != NULL)
    strcpy(p, s);
  return p;
}

char buf[BUFSIZE];
int bufp = 0;

int getch(void)    
{
  return (bufp > 0) ? buf[--bufp] : getchar();
}

void ungetch(int c)
{
  if (bufp >= BUFSIZE)
    printf("ungetch: too many characters\n");
  else
    buf[bufp++] = c;
}
   
int getword(char *word, int lim)
{
  int c, getch(void);
  void ungetch(int);
  char *w = word;

  while (isspace(c = getch()))
    ;

  // ignore string constants
  if (c == '"') {
    while((c = getch()) != '"' && c != EOF)
      ;
  }

  // ignore comments
  if (c == '/') {
    c = getch();
    if (c == '/')
      while ((c = getch()) != '\n' && c != EOF)
        ;
    else if (c == '*') {
      while (c != EOF) {
        if ((c = getch())  == '*' && (c = getch()) == '/')
          break;
      }
    }
  }
  
  if (c != EOF)
    *w++ = c;
  if (!isalpha(c)) {
    *w = '\0';
    return c;
  }
  for ( ; --lim > 0; w++) {
    if (!isalnum(*w = getch())) {
      ungetch(*w);
      break;
    }
  }
  *w = '\0';
  return word[0];
}

// strcmp with all the messy casting
int mystrcmp(const void * s, const void * t) {
  const char *_s = *(const char **)s;
  const char *_t = *(const char **)t;
  int x = strcmp(_s, _t);
  return x;
}
