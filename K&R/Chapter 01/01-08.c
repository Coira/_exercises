#include <stdio.h>

main()
{
  int c, nc, bc, tc;

  nc = 0;
  bc = 0;
  tc = 0;
  while((c = getchar()) != EOF) {
    if (c == '\n')
      ++nc;
    if (c == ' ')
      ++bc;
    if (c == '\t')
      ++tc;
  }

  printf("There were %.0d new lines, %.0d blanks and %.0d tabs", nc, bc, tc);
  
}
