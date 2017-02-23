#include <string.h>
#include <stdio.h>

void reverse(char s[]);
void reverse_helper(char s[], int i, int j);
void swap(char v[], int i, int j);

int main()
{
  char s[] = "The big fat cat sat on the tiny mat.";
  reverse(s);
  printf("%s\n", s);
  
  return 0;
}

void reverse(char s[])
{
  reverse_helper(s, 0, strlen(s)-1);
}

void reverse_helper(char s[], int i, int j)
{
  if (i < j) {
    swap(s, i, j);
    reverse_helper(s, i+1, j-1);
  }
}

void swap(char v[], int i, int j)
{
  int temp;

  temp = v[i];
  v[i] = v[j];
  v[j] = temp;
}

    
