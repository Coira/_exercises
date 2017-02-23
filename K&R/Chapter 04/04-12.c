#include <stdio.h>
#include <stdlib.h>

void printd(int);
int itoax(int, char[]);

int main()
{
  char s[1000];
  itoax(-34, s);
  printf("%s\n",s);
  return 0;
}

int itoax(int n, char s[])
{
  int i = 0;

  if (n < 0)
    s[i++] = '-';
  if (n / 10)
    i = itoax(n / 10, s);

  s[i++] = (abs(n) % 10 + '0');
  s[i] = '\0';
  
  
  return i;
  
}
  
void printd(int n)
{
  if (n < 0) {
    putchar('-');
    n = -n;
  }

  if (n / 10)
    printd(n / 10);
  putchar(n % 10 + '0');

}

  
