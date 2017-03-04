#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAXLINES 5000
#define MAXLEN 1000
#define ALLOCSIZE 10000

void tail(char* [], int);
int getline(char *);
char *alloc(int);
int readlines(char *lineptr[], int);

char *lineptr[MAXLINES];
static char allocbuf[ALLOCSIZE];
static char *allocp = allocbuf;

int main(int argc, char* argv[])
{
  int nlines;
  int n;

  if (argc < 2 || (!isdigit(*argv[1]))) {
      printf("Setting n to 10\n");
      n = 10;
  }
  else
    n = atoi(argv[1]);
      
    
  nlines = readlines(lineptr, MAXLINES);

  if (nlines < n)
    n = nlines;
  
  int i;
  for (i = nlines - n; i < nlines; i++) {
    printf("%s\n", lineptr[i]);
  }
  
  return 0;
}

int getline(char *s)
{
  int i = 0;
  while ((*s++ = getchar()) != '\n')
    ++i;
  *--s = '\0';
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

int readlines(char *lineptr[], int maxlines)
{
  int len, nlines;
  char *p, line[MAXLEN];

  nlines = 0;
  while ((len = getline(line)) > 0) {
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
  
