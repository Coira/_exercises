#include <stdio.h>

#define TRUE 1
#define FALSE 0

main()
{
  int c;
  int suppress_line = FALSE;
  
  while ((c = getchar()) != EOF) {
    if (c == ' ' || c == '\t' || c == '\n') {
      if (!suppress_line) {
        putchar('\n');
        suppress_line = TRUE;
      }
    }
    else {
      putchar('*');
      suppress_line = FALSE;
    }
  }
}
