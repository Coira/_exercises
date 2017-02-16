#include <stdio.h>

unsigned invert(unsigned x, int p, int n);

int main()
{
  unsigned x = 170;
  printf("%d\n", invert(x, 5, 4)); // should return 150
}

unsigned invert(unsigned x, int p, int n)
{
  return x ^ ((1 << n)-1) << p+1-n;
}
