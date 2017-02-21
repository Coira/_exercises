#include <stdio.h>
#include <string.h>
#include <limits.h>
#define MAXLINE 1000

void itob(int n, char s[], int b);
void reverse(char s[]);
int abs(int n);

int main()
{
  char s[MAXLINE];
  itob(3383,s,16);
  printf("%s\n",s);
  
}

void itob(int n, char s[], int b)
{
  int i, sign;
  int d;
  sign = (n < 0) ? -1 : 1;

  i = 0;
  do {
    d = abs(n % b);
    d += (d > 9) ? 'A' - 10 : '0';  // don't forget to start counting ascii from 10
    s[i++] = d;
  } while (abs(n /= b) > 0);

  if (sign < 0)
    s[i++] = '-';
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
 
