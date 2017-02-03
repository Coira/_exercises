#include <stdio.h>
#define MAXLINE 1000

int getline(char s[], int maxline);
void copy(char to[], char from[]);
void reverse(char s[], int length);

main()
{
  int len;
  char line[MAXLINE];

  while((len = getline(line, MAXLINE)) > 0) {
    reverse(line, len);
    printf("Reversed: %s\n", line);
  }

  return 0;

}

void reverse(char s[], int length) {
  char r[length];  // temporarily copy reversed string to here
  int i, j;

  j = 0;
  if (s[length-1] == '\n') length--;  // skip past new line char
  for (i = length-1; i >= 0; i--, j++) 
    r[j] = s[i];
  r[j] = '\0';

  copy(s, r);  // copy reversed string back to original array
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

/* copy: copy 'from' into 'to'; assume to is big enough */
void copy(char to[], char from[])
{
    int i;

    i = 0;
    while ((to[i] = from[i]) != '\0')
        ++i;
}
