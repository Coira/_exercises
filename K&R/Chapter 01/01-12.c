#include <stdio.h>

main()
{
  int c;
  int nl = 0;  // suppress multiple blank lines
  
  while((c = getchar()) != EOF) {
    if (c == ' ' || c == '\t' || c == '\n') {
      if (nl == 0) { 
        putchar('\n');
        nl = 1;
      }
    }
    else {
      putchar(c);
      nl = 0;
    }
  }
}
