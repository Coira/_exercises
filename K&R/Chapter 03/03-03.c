#include <stdio.h>
#define MAXLINE 1000

void expand(char s1[], char s2[]);
                                
int main()
{
  char s[50] = "-a-zA-Z-0-9a-b-dd-a-";
  char t[MAXLINE];
  expand(s,t);
  printf("%s\n", t);
  return 0;
}

void expand(char s[], char t[])
{
  int i, j, c;

  for (i = 0, j = 0; s[i] != '\0'; i++) {
    // check that '-' isn't leading or trailing, and that
    // it's, for example, a-z and not z-a

    if (s[i] == '-' && i > 0 && (s[i-1] < s[i+1])) {
      int k = s[i-1] + 1; // we've already added the first letter
      while (k <= s[i+1]) {
        t[j++] = k++;
      }

      i++;
    }

    else t[j++] = s[i];
  }

  t[j] = '\0';
          
}


