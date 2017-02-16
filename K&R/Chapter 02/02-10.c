#include <stdio.h>

int lower(int c);

int main()
{
  printf("%c %c %c\n", lower('A'), lower('a'), lower('Z'));
  
  return 0;
}
int lower(int c)
{
    return (c >= 'A' && c <= 'Z') ? c + 'a' - 'A' :c;

}
