#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#include "user_input.c"
#include "stack.c"

#define MAXOP 100
#define NUMBER '0'
#define FUNC 1
#define VARIABLE 2

int getop(char []);
void push(double);
double pop(void);
void duplicate(void);
void swap(void);
void extra_funcs(char []);

main()
{
  int type;
  double op2;
  char s[MAXOP];

  while ((type = getop(s)) != EOF) {
    switch (type) {
    case NUMBER:
      push(atof(s));
      break;
    case FUNC: // extra maths functions
      extra_funcs(s);
      break;
    case '+':
      push(pop() + pop());
      break;
    case '*':
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
      
    case 't': // print top of stack
      if (sp > 0) {
        printf("stack: %g\n", val[sp-1]);
      }
      else
        printf("error: stack is empty\n");
      break;
    case 'd': // duplicate top of stack
      duplicate();
      break;
    case 's': // swap top two elements
      swap();
      break;
      
    case '\n':
      printf("\t%.8g\n", pop());
      break;
    default:
      printf("error: unknown command %s\n", s);
      break;
    }
  }
  return 0;
}

void duplicate(void)
{
  if (sp > 0)
    push(val[sp-1]);
  else
    printf("error: no items to duplicate\n");
}

void swap(void)
{
  if (sp > 1) {
    double t1 = pop();
    double t2 = pop();

    push(t1);
    push(t2);
  }
  else
    printf("error: no items to swap\n");
}

void extra_funcs(char s[])
{
  if (strcmp(s, "sin") == 0) {
    if (sp > 0)
      push(sin(pop()));
    else
      printf("error: no items on the stack\n");
  }
  else if (strcmp(s, "exp") == 0) {
    if (sp > 0)
      push(exp(pop()));
    else
      printf("error: no items on the stack\n");
  }
  else if (strcmp(s, "pow") == 0) {
    if (sp > 0) {
      double op2 = pop();
      push(pow(pop(), op2));
    }
    else
      printf("error: no items on the stack\n");
  }    
}

int getop(char s[])
{
  int i, c;

  while ((s[0] = c = getch()) == ' ' || c == '\t')
    ;
  s[1] = '\0';

  i = 0;

  // get maths function command
  if (isalpha(c)) {
    while(isalpha(s[++i] = c = getch()))
      ;
    s[i] = '\0';
    if (c != EOF)
      ungetch(c);
    return FUNC;
  }
      
  if (!isdigit(c) && c != '.' && c != '-')
    return c;   

  if (c == '-') { /* negative numbers */
    c = getch();
    if (isdigit(c))
      ungetch(c);
    else {
      ungetch(c);
      return '-';
    }
  }
  
  if (isdigit(c))
    while (isdigit(s[++i] = c = getch()))
      ;
  if (c == '.')
    while (isdigit(s[++i] = c = getch()))
      ;
    
  s[i] = '\0';
  if (c != EOF)
    ungetch(c);
  return NUMBER;
}
