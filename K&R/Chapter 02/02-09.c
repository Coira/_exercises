#include <stdio.h>

int bitcount(unsigned x);

int main() {
  printf("%d\n", bitcount(170));
}

int bitcount(unsigned x)
{
  int b;
  for (b = 0; x != 0; x &= (x-1)) {
      b++;
  }

  return b;
}
    
