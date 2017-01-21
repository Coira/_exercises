#include <stdio.h>

main()
{
  int c;
  int chars[26];
                    
  int i;
  for (i = 0; i < 26; i++) {
    chars[i] = 0;
  }

  while((c = getchar()) != EOF) {
    if (c >= 'a' && c <= 'z') {
      chars[c - 'a']++;
    }
    // lower and upper letters get counted the same
    else if (c >= 'A' && c <= 'Z') {
      chars[c - 'A']++;
    }
  }

  for(i = 0; i < 26; i++) {
    printf("%c |", i + 'A');

    int j;
    for(j = 0; j < chars[i]; j++) {
      putchar('*');
    }
    putchar('\n');
  }
}

  
