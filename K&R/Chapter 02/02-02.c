#include <stdio.h>

int main() {
  int i;
  int c;
  int s[100];
  int lim = 100;

  /*
  for (i = 0; i < lim-1 && (c = getchar()) != '\n' && c != EOF; ++i)
    s[i] = c;
  */

  for (i = 0; i < lim-1; ++i) {
    c = getchar();
    if (c == '\n') i = lim;
    if (c == EOF) i = lim;
  }

  return 0;
}
