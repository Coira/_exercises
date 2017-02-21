#include <stdio.h>
#define MAXLINE 1000

void escape(char s[], char t[]);
void unescape(char s[], char t[]);

int main()
{
  char s[MAXLINE/2] = "This is a sentence \t with \n some \n special \t characters";
  char t[MAXLINE];
  char u[MAXLINE];
  
  escape(s, t);
  printf("%s\n", s);
  printf("%s\n", t);
  unescape(t, u);
  printf("%s\n", u);
  return 0;
}

void escape(char s[], char t[])
{
  int i = 0; 
  int j = 0;
  char c;
  
  // assuming that t is big enough
  while ((c = s[i]) != '\0' && c != EOF) {
    switch(c) {
    case '\t':
      t[j++] = '\\';
      t[j++] = 't';
      break;
    case '\n':
      t[j++] = '\\';
      t[j++] = 'n';
      break;
    default:
      t[j++] = s[i];
      break;
    }
    i++;

  }

  t[j] = '\0';
}

void unescape(char s[], char t[])
{
  int i = 0;
  int j = 0;
  char c;

  while (s[i] != '\0' && s[i] != EOF) {
    if (s[i] == '\\') {
      switch(s[++i]) {
      case 't':
        t[j++] = '\t';
        break;
      case 'n':
        t[j++] = '\n';
        break;
      default:
        t[j++] = '\\';
        t[j++] = s[i];
        break;
      }
      i++;
    }
    else {
      t[j++] = s[i++];
    }

    t[j] = '\0';
  }
}
