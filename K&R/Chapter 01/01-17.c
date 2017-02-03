#include <stdio.h>
#define MAXLINE 80

int getline(char line[], int maxline);

main()
{
  int c;
  int len;
  char line[MAXLINE+1];
  
  while((len = getline(line, MAXLINE+1)) > 0) {
    if (len >= MAXLINE-1) {
      printf("%s", line);
      while ((c = getchar()) != EOF && c != '\n')
        putchar(c);
    }
  }
  return 0;
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
