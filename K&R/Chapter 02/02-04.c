#include <stdio.h>

void squeeze(char s[], char r[]);

int main()
{
  char s[50] = "This string without any vowels looks like\0";
  
  printf("%s: ", s);
  squeeze(s, "aeiou\0");
  printf("%s", s);
  
  return 0;
}

void squeeze(char s[], char r[])
{
  int i,j,k;
    
  for (i = 0; r[i] != '\0'; i++) {
      
    for (j = k = 0; s[j] != '\0'; j++) {
      if (s[j] != r[i])
        s[k++] = s[j];
    }
        
    s[k] = '\0';
  }
}

