#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

#define MAXLINES 5000
#define MAXLEN 1000
#define ALLOCSIZE 10000

char *lineptr[MAXLINES];

int readlines(char *lineptr[], int nlines);
void writelines(char *lineptr[], int nlines);
int getline(char s[], int);
char *alloc(int);

void qsortx(void *lineptr[], int left, int right, int reverse,
           int(*comp)(void *, void *));
int numcmp(char *, char *);
int dircmp(char *, char *);
int dirfcmp(char *, char *);

char *lineptr[MAXLINES];
static char allocbuf[ALLOCSIZE];
static char *allocp = allocbuf;

int main(int argc, char *argv[])
{
  
  int nlines;
  int numeric = 0;
  int reverse = 0;
  int fold = 0;
  int directory = 0;
  
  int (*comp)(void*, void*, int);  // function qsort should use to compare
  
  while(argc-- > 0) {
    if (strcmp(argv[argc], "-n") == 0)
      numeric = 1;
    else if (strcmp(argv[argc], "-r") == 0)
      reverse = 1;
    else if (strcmp(argv[argc], "-f") == 0)
      fold = 1;
    else if (strcmp(argv[argc], "-d") == 0)
      directory = 1;
  }

  // work out which compare func to use
  // which flag combinations are valid?
  if (numeric)
    comp = numcmp;
  else if (fold && directory)
    comp = dirfcmp;
  else if (fold)
    comp = strcasecmp;
  else if (directory)
    comp = dircmp;
  else
    comp = strcmp;
  
  if ((nlines = readlines(lineptr, MAXLINES)) >= 0) {
    //qsortx((void **) lineptr, 0, nlines-1, reverse,
    //      (int (*)(void*, void*))(numeric ? numcmp : strcmp));
    qsortx((void **) lineptr, 0, nlines-1, reverse, comp);
          
    writelines(lineptr, nlines);
    return 0;
  } else {
    printf("input too big to sort\n");
    return 1;
  }
}

void qsortx(void *v[], int left, int right, int reverse,
           int (*comp)(void *, void *))
{
  
  int i, last;
  void swap(void *v[], int, int);

  if (left >= right)
    return;
  swap(v, left, (left + right)/2);
  last = left;

  if (reverse) {
    for (i = left+1; i <= right; i++)
      if ((*comp)(v[i], v[left]) > 0)
        swap(v, ++last, i);
  }
  else {
    for (i = left+1; i <= right; i++)
      if ((*comp)(v[i], v[left]) < 0)
        swap(v, ++last, i);
  }
  swap(v, left, last);
  qsortx(v, left, last-1, reverse, comp);
  qsortx(v, last+1, right, reverse, comp);
}

int dirfcmp(char *s1, char *s2)
{
  while(*s1++ & *s2++) {
    char c1 = tolower(*s1);
    char c2 = tolower(*s2);
    
    if ((!isalnum(c1) && !isspace(c1)) ||
        (!isalnum(c2) && !isspace(c2))) {
      // don't compare
    }
    else {
      if (c1 < c2) return -1;
      else if (c1 > c2) return 1;
      // continue comparision
    }
  }
  return 0;
}
  
int dircmp(char *s1, char *s2)
{
  
  while(*s1++ & *s2++) {
    if ((!isalnum(*s1) && !isspace(*s1)) ||
        (!isalnum(*s2) && !isspace(*s2))) {
      // don't compare
    }
    else {
      if (*s1 < *s2) return -1;
      else if (*s1 > *s2) return 1;
      // continue comparision
    }
  }
  return 0;
}
         
int numcmp(char *s1, char *s2)
{
    double v1, v2;

    v1 = atof(s1);
    v2 = atof(s2);
    if (v1 < v2)
        return -1;
    else if (v1 > v2)
        return 1;
    else
        return 0;
}

void swap(void *v[], int i,  int j)
{
    void *temp;
    
    temp = v[i];
    v[i] = v[j];
    v[j] = temp;
}

int readlines(char *lineptr[], int maxlines)
{
  int len, nlines;
  char *p, line[MAXLEN];

  nlines = 0;
  while ((len = getline(line, MAXLEN)) > 0) {
    if (nlines >= maxlines || (p = alloc(len)) == NULL)
      return -1;
    else {
      line[len-1] = '\0';
      strcpy(p, line);
      lineptr[nlines++] = p;
    }
  }
  return nlines;
}
  
int getline(char s[], int lim)
{
  int c, i;
  i = 0;
  while (--lim > 0 && (c=getchar()) != EOF && c != '\n')
    s[i++] = c;
  if (c == '\n')
    s[i++] = c;
  s[i] = '\0';
  return i;
}

char *alloc(int n)
{
  if (allocbuf + ALLOCSIZE - allocp >= n) {
    allocp += n;
    return allocp - n;
  } else
    return 0;
}

void writelines(char *lineptr[], int nlines)
{
    int i;
    for (i = 0; i < nlines; i++)
        printf("%s\n",lineptr[i]);
}
