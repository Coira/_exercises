#include <stdio.h>

unsigned getbits(unsigned x, int p, int n);
unsigned setbits(unsigned x, int p, int n, unsigned y);

int main()
{
  unsigned x = 170;
  unsigned y = 6;

  /* sets 4 bits of x, starting at position 5 and going right,
     to the rightmost 4 bits of y */
  printf("%d\n", setbits(x, 5,4,y)); // should equal 154
  printf("%d\n", setbits(0, 0, 1, 17)); // should equal 1
}

unsigned getbits(unsigned x, int p, int n)
{
  return (x >> (p+1-n)) & ~(~0 << n);
}

unsigned setbits(unsigned x, int p, int n, unsigned y)
{
  // the right-most n bits of y, shifted to position p
  unsigned bits = getbits(y, n-1, n) << p+1-n;

  // create a mask using x, so that the bits we're changing in x
  // are zero'd out
  int mask = x & (~(~(~0 << p-1) << p+1-n));

  // add in the bits from y in the correct position of x
  return mask | bits;
}
