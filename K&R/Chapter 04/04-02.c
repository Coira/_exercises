#include <ctype.h>
#include <stdio.h>

double atof2(char s[]);

int main()
{
  printf("%f\n", atof("3.1417e7"));
  return 0;
}

double atof2(char s[])
{
  double val, power, exp;
  int i, sign;
  int pow = 1;
  
  for (i = 0; isspace(s[i]); i++) //skip white space
    ;

  sign = (s[i] == '-') ? -1 : 1;
  if (s[i] == '+' || s[i] == '-')
    i++;
  for (val = 0.0; isdigit(s[i]); i++)
    val = 10.0 * val + (s[i] - '0');
  if (s[i] == '.')
    i++;
  for (power = 1.0; isdigit(s[i]); i++) {
    val = 10.0 * val + (s[i] - '0');
    power *= 10.0;
  }
  
  //return sign * val / power;

  // check for exponent
  if (s[i] == 'e' || s[i] == 'E') {
    i++;
    sign = (s[i] == '-') ? -1 : 1;
    if (s[i] == '+' || s[i] == '-')
      i++;

    exp = 0;
    while (isdigit(s[i])) {
      exp = 10.0 * exp + (s[i] - '0');
      i++;
    }

    for(i=0; i < exp; i++)
      pow *= 10;
  }

  return
    (sign == 1)
    ? (val * pow) / power
    : val / (pow * power);
  
}

    
  
  
