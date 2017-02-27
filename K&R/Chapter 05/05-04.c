#include <stdio.h>
#include <string.h>

#define MAXLINE 1000

int strend(char s[], char t[]);

int main()
{
  char s[1000] = "0123456789";
  char t[1000] = "678";
  printf("%d\n", strend(s, t));
  return 0;
}

int strend(char *s, char *t)
{
  int slen = strlen(s);
  int tlen = strlen(t);

  if (tlen > slen)
    return 0;

  for(s+=slen-tlen; *s != '\0'; s++, t++) {
    if (*s != *t)
      return 0;
  }

  return 1;

}
