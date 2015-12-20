#lang racket

(define rember
  (lambda (a lat)
    (cond
      ((null? lat) '())
      ((eq? (car lat) a) (cdr lat))
      (else (cons (car lat)
                  (rember a (cdr lat)))))))

(rember 'bacon '(orange bacon lettuce and tomato))

(define firsts
  (lambda (l)
    (cond
      ((null? l) '())
      (else
       (cons (car (car l))
             (firsts (cdr l)))))))

(firsts '((apple peach pumpkin)
         (plum pear cherry)
         (grape raisin pea)
         (bean carrot eggplant)))

(define insertR
  (lambda (new old lat)
    (cond
      ((null? lat) '())
      ((eq? (car lat) old) (cons old (cons new (cdr lat))))
      (else
       (cons (car lat) (insertR new old (cdr lat)))))))

(insertR 'e 'd '(a b c d f g h))

(define insertL
  (lambda (new old lat)
    (cond
      ((null? lat) '())
      ((eq? (car lat) old) (cons new lat))
      (else
       (cons (car lat) (insertL new old (cdr lat)))))))

(insertL 'three 'four '(one two four five))

(define subst
  (lambda (new old lat)
    (cond
      ((null? lat) '())
      ((eq? (car lat) old) (cons new (cdr lat)))
      (else
       (cons (car lat) (subst new old (cdr lat)))))))

(subst 'sweet 'spicy '(a tub of spicy popcorn))

(define subst2
  (lambda (new o1 o2 lat)
    (cond
      ((null? lat) '())
      (((eq? (car lat) o1) (eq? (car lat) o2)) (cons new (cdr lat)))
      (else
       (cons (car lat) (subst2 (cdr lat)))))))

(subst2 'vanilla 'chocolate 'banana '(banana ice cream with chocolate topping))
     


       
        
