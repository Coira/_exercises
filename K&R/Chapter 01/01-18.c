#include <stdio.h>
#define MAXLINE 1000

int getline(char s[], int maxline);
int removeblanks(char line[], int length);
void copy(char to[], char from[]);

main()
{
    int len;    
    int max;    
    char line[MAXLINE];     

    max = 0;
    while ((len = getline(line, MAXLINE)) > 0) {
        len = removeblanks(line,len);
        if (len > 0)
          printf("New string: \"%s\"\n", line);
    }

    return 0;

}

/* remove leading and trailing blanks and return length of new string */
int removeblanks(char line[], int length)
{
    char str[length];
    int i, k;
    int j;
    int c;

    i = 0;
    // find position of first non-blank char
    while(line[i] == ' ' || line[i] == '\t')
      i++;

    // find first non-blank char postion at the *end* of the line
    j = length-1;
    while(line[j] == '\n' || line[j] == '\t' || line[j] == ' ')
      j--;

    // copy the old string from position i to position j to a new string
    for(k = 0; k <= j-i; k++)
      str[k] = line[k+i];
    str[k] = '\0';
    
    copy(line, str);
    return k;
}

int getline(char s[], int lim)
{
    int c, i;

    for (i=0; i<lim-1 && (c=getchar()) != EOF && c!='\n'; ++i)
        s[i] = c;
    if (c == '\n') {
        s[i] = c;
        ++i;
    }
    s[i] = '\0';
    return i;
}

/* copy: copy 'from' into 'to'; assume to is big enough */
void copy(char to[], char from[])
{
    int i;

    i = 0;
    while ((to[i] = from[i]) != '\0')
        ++i;
}
