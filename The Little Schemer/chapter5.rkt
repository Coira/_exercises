#lang racket


(define atom?
  (lambda (x)
    (and (not (pair? x)) (not (null? x)))))

(define rember*
  (lambda (a l)
    (cond ((null? l) '())
          ((atom? (car l))
           (cond ((eq? a (car l)) (rember* a (cdr l)))
                 (else (cons (car l) (rember* a (cdr l))))))
          (else (cons (rember* a (car l))
                      (rember* a (cdr l)))))))



(define insertR
  (lambda (new old l)
    (cond ((null? l) '())
          ((eq? old (car l)) (cons old (cons new (cdr l))))
          (else (cons (car l) (insertR new old (cdr l)))))))

(define insertR*
  (lambda (new old l)
    (cond ((null? l) '())
          ((atom? (car l))
           (cond ((eq? old (car l))
                  (cons old (cons new (insertR* new old (cdr l)))))
                 (else (cons (car l) (insertR* new old (cdr l))))))
           (else (cons (insertR* new old (car l))
                       (insertR* new old (cdr l)))))))
                 
                     

(rember* 'cup '((coffee) cup ((tea) cup) (and (hick)) cup))
(insertR* 'roast 'chuck '((how much (wood)) could ((a (wood) chuck)) (((chuck))) (if (a) ((wood chuck))) could chuck wood))