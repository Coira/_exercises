#include <stdio.h>

int any(char s1[], char s2[]);

int main()
{
  char s[50] = "The first vowel occurs at position:\0";
  printf("%s %d\n", s, any(s, "aeiou\0"));

  char t[50] = "Thr r n vwls n ths sntnc\0";
  printf("%s %d\n", t, any(t, "aeiou"));
  return 0;
}

int any(char s1[], char s2[])
{
  int i, j;
 
  for (i = 0; s1[i] != '\0'; i++) {
    for (j = 0; s2[j] != '\0'; j++) {
      if (s1[i] == s2[j]) return i;
    }
  }

  return -1;
}
