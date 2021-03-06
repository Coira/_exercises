#include <stdio.h>
#include <string.h>
#include <limits.h>
#define MAXLINE 1000

void itoa(int n, char s[], int w);
void reverse(char s[]);
int abs(int n);

int main()
{
  char s[MAXLINE];
  itoa(-239,s,10);
  printf("%s\n",s);
  
}

void itoa(int n, char s[], int w)
{
  int i, sign;

  sign = (n < 0) ? -1 : 1;

  i = 0;
  do {
    s[i++] = abs(n % 10) + '0';
  } while (abs(n /= 10) > 0);

  if (sign < 0)
    s[i++] = '-';

  while (i < w)
    s[i++] = ' ';
  
  s[i] = '\0';
  reverse(s);
}

void reverse(char s[])
{
  int c, i, j;

  for (i = 0, j = strlen(s)-1; i < j; i++, j--) {
    c = s[i];
    s[i] = s[j];
    s[j] = c;
  }
}

int abs(int n)
{
  return (n < 0 ? n*-1 : n);
}
