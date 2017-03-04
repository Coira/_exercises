#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>
#include <ctype.h>

#include "stack.c"

#define MAXOP 100
#define NUMBER '0'

int getop(char [] );
void push(double);
double pop(void);


main(int argc, char *argv[])
{
  double op2;
  char s[MAXOP];
  char c;
  
  int i = 1;
  while (*++argv) {
    switch (getop(*argv)) {
    case NUMBER:
      push(atof(*argv));
      break;
    case '+':
      push(pop() + pop());
      break;
    case '*':  // windows command line has a problem with *
    case 'x':
      push(pop() * pop());
      break;
    case '-':
      op2 = pop();
      push(pop() - op2);
      break;
    case '/':
      op2 = pop();
      if (op2 != 0.0)
        push(pop() / op2);
      else
        printf("error: zero divisor\n");
      break;
      
    case '%': // modulo
      op2 = pop();
      if (op2 != 0.0)
        push(fmod(pop(),op2)); //use fmod for doubles, fmodf for floats
      else
        printf("error: zero divisor\n");
      break;      
    default:
      printf("error: unknown command %s\n", s);
      break;
    }

  }
  printf("\t%.8g\n", pop());
  return 0;
}

int getop(char s[])
{
  char c = s[0];
  if (!isdigit(c) && c != '.' && c != '-')
    return s[0];   
  else
    return NUMBER;
}
