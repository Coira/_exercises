#include <stdio.h>
#include <float.h>
#include <limits.h>

// char short int long signed unsigned float

int main() {
  printf("Char\n");
  printf("  Signed: %d to %d\n", CHAR_MIN, CHAR_MAX);
  printf("  Unsigned: %d to %d\n",0, UCHAR_MAX);
  printf("Int\n");
  printf("  Signed: %d to %d\n", INT_MIN, INT_MAX);
  printf("  Unsigned: %d to %u\n", 0, UINT_MAX);
  printf("Short\n");
  printf("  Signed: %d to %d\n", SHRT_MIN, SHRT_MAX);
  printf("  Unsigned: %d to %d\n", 0, USHRT_MAX);
  printf("Long\n");
  printf("  Signed: %ld to %ld\n", LONG_MIN, LONG_MAX);
  printf("  Unsigned: %ld to %ul\n", 0, ULONG_MAX);
  printf("Float\n");
  printf("  Signed: %f to %f\n", FLT_MIN, FLT_MAX);

  return 0;
}
