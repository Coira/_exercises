#include <stdio.h>

#define TRUE 1
#define FALSE 0

main()
{
  int c;
  int suppress = FALSE;
  
  while ((c = getchar()) != EOF) {
      if (suppress == TRUE && c == ' ')
        ; // do nothing
      else if (c == ' ') {
        suppress = TRUE;
        putchar(c);
      }
      else {
        suppress = FALSE;
        putchar(c);
      }
  }
}
  
