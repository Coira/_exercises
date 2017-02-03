#include <stdio.h>
#include <ctype.h>
#define MAXLINE 128

int htoi(const char s[]);
int getline(char line[], int maxline);

int main() {
  int c, len;
  char s[MAXLINE+1];

  while((len = getline(s, MAXLINE+1)) > 0) {
    printf("%s > %d\n", s, htoi(s));
  }
  
  return 0;
}

int htoi(const char s[])
{
  int value = 0;
  int len, i;
    
  if (s[0] == '0' && (s[1] == 'x' || s[1] == 'X'))
    i = 2;
  else
    i = 0;

  int c;
  while((c = s[i]) != '\0' && c != EOF && c != '\n') {

    if (isalpha(c) && islower(c))
        c = toupper(c);
        
    if (c >= 'A' && c <= 'F')
      value = (value * 16) + (c - 'A' + 10);
    else
      value = (value * 16) + (c - '0');
    
    ++i;
  }

  return value;    
}

int getline(char s[], int lim)
{
  int c, i;

  for (i=0; i<lim-1 && (c=getchar()) != EOF && c!='\n'; ++i)
    s[i] = c;
  

  s[i] = '\0';
  return i;
}
