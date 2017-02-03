#include <stdio.h>
#define MAXLINE 50

int getline(char line[], int maxline);
void copy(char to[], char from[]);

main()
{
  int len;
  int max;
  char line[MAXLINE];
  char longest[MAXLINE];

  max = 0;
  while((len = getline(line, MAXLINE)) > 0) {

    if (len == MAXLINE - 1 && line[MAXLINE-1] != '\n') {
      int c;
      while ((c = getchar()) != EOF && c != '\n')
        len++;
    }

    if (len > max) {
      max = len;
      copy(longest, line);
    }
    
  }

  printf("The longest line contains %d characters.\n", max);
  if (max > MAXLINE)
    printf("The first %d characters were: %s", MAXLINE, longest);
  else if (max > 0)
    printf("It said: %s", longest);
  
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

void copy(char to[], char from[])
{
  int i;

  i = 0;
  while ((to[i] = from[i]) != '\0')
    ++i;
}
