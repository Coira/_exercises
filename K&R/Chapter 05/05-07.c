#include <stdio.h>
#include <string.h>

#define MAXLINES 5000
#define MAXLEN 1000
#define ALLOCSIZE 10000

char lineptr[MAXLINES][MAXLEN];
static char allocbuf[ALLOCSIZE];
static char *allocp = allocbuf;

int readlines(char lineptr[][MAXLEN], int nlines);
void writelines(char lineptr[][MAXLEN], int nlines);
void qsort(char *lineptr[], int left, int right);
void swap(char *v[], int i, int j);
int getline(char *, int);
char *alloc(int);

main()
{
  int nlines;

  if ((nlines = readlines(lineptr, MAXLINES)) >= 0) {
    writelines(lineptr, nlines);
    return 0;
  }
}

int readlines(char lineptr[][MAXLEN], int maxlines)
{
  int len, nlines;
  char *p, line[MAXLEN];

  nlines = 0;
  while ((len = getline(lineptr[nlines], MAXLEN)) > 0) {
    if (nlines >= maxlines)
      return -1;
    else {
      lineptr[nlines++][len-1] = '\0';
    }
  }
  return nlines;
}

void writelines(char lineptr[][MAXLEN], int nlines)
{
  int i;

  for (i = 0; i < nlines; i++)
    printf("%s\n",lineptr[i]);
}

int getline(char s[], int lim)
{
  int c, i;

  for (i=0; i<lim-1 && (c=getchar()) != EOF && c!='\n'; ++i)
    s[i] = c;
  if (c == '\n') {
    s[i] = c;
    ++i;
  }
  s[i] = '\0';
  return i;
}

/* return pointer to n characters */
char *alloc(int n)
{
  if (allocbuf +ALLOCSIZE - allocp >= n) {
    allocp += n;
    return allocp - n;
  } else
    return 0;
}

/* qsort: sort into increasing order */
void qsort(char *v[], int left, int right)
{
  int i, last;
    
  if (left >= right)
    return;
  swap(v, left, (left + right)/2);
  last = left;
  for (i = left+1; i <= right; i++)
    if (strcmp(v[i], v[left]) < 0)
      swap(v, ++last, i);
  swap(v, left, last);
  qsort(v, left, last-1);
  qsort(v, last+1, right);
}

void swap(char *v[], int i, int j)
{
  char *temp;

  temp = v[i];
  v[i] = v[j];
  v[j] = temp;
}
