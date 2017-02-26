#include <stdio.h>

#define MAXLINE 1000

void strcatx(char s[], char t[]);
void strcatt(char *s, char *t);  // renamed -- clashes with original strcat

int main()  
{
  /* compiler notes
   * char *s = "some sentence"  --> this creates runtime error
   * char s[] = "some sentence" --> so does this
   */
  
  char s[MAXLINE] = "what's this for?";
  char t[MAXLINE] = "some random sentence";

  strcatt(s, t);
  printf("%s\n%s\n", s, t);
  
  return 0;
}

void strcatt(char *s, char *t)
{
  while (*s++)  
    ;
  s--;
  while(*s++ = *t++)
    ;
}

void strcatx(char s[], char t[])
{
  int i, j;

  i = j = 0;
  while (s[i] != '\0')
    i++;
  while ((s[i++] = t[j++]) != '\0')
    ;
}

      
     
