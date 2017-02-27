#include <stdio.h>
#include <string.h>

#define MAXLINE 1000

char *strncpyx(char *s, char *ct, int n);
char *strncatx(char *s, char *ct, int n);
int strncmpx(char *cs, char *ct, int n);

int main()
{
  char s[MAXLINE] = "This is the first sentence. ";
  char t[MAXLINE] = "And this is the second.";
  printf("%s\n", strncatx(s,t,4));

  char u[MAXLINE] = "Compare me";
  char v[MAXLINE] = "Compare mex";
  printf("%d\n", strncmpx(u,v,strlen(v)));
  return 0;
}

/* Copy at most n chars of ct to s; return s.
   Pad with '\0' if t has fewer than n chars */
char *strncpyx(char *s, char *ct, int n)
{
  // need to create temporary pointer to s, so that
  // s points to the start of the string when we return it
  char *p = s;
  
  int i;
  for (i = 0; i < n && *ct != '\0'; i++) {
    *p++ = *ct++;
  }
  *p = '\0';

  return s;
}

/* Concatenate at most n chars of ct to s, terminate with '\0'.
   Return s. */
char *strncatx(char *s, char *ct, int n)
{
  char *p = s;
  int i;
  
  p += strlen(p);

  for (i = 0; i < n && *ct != '\0'; i++) {
    *p++ = *ct++;
  }
  *p = '\0';

  return s;
  
}

/* Compare at most n chars of cs to ct.
   Return <0 if cs<ct, 0 if cs==ct, >0 if cs>ct */
int strncmpx(char *cs, char *ct, int n)
{
  int i;
  for (i = 0; i < n && *cs == *ct; cs++, ct++, i++) {
    if (*cs == '0')
      return 0;
  }
  return *cs - *ct;
}
