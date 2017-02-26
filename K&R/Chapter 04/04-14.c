#include <stdio.h>

#define swap(t, x, y) {t v = x; x = y; y = v;}

int main()
{
  int x = 1;
  int y = 2;
  swap(int, x, y);
  printf("%d, %d\n", x, y);
  return 0;
}


