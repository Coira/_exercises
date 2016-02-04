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

(define occur*
  (lambda (a l)
    (cond ((null? l) 0)
          ((atom? (car l))
           (cond ((eq? a (car l)) (+ 1 (occur* a (cdr l))))
                 (else (occur* a (cdr l)))))
          (else (+ (occur* a (car l)) (occur* a (cdr l)))))))

(define subst*
  (lambda (new old l)
    (cond ((null? l) '())
          ((atom? (car l))
           (cond ((eq? old (car l)) (cons new (subst* new old (cdr l))))
                 (else (cons (car l) (subst* new old (cdr l))))))
          (else (cons (subst* new old (car l)) (subst* new old (cdr l)))))))

(define insertL*
  (lambda (new old l)
    (cond ((null? l) '())
          ((atom? (car l))
           (cond ((eq? old (car l)) (cons new (cons old (insertL* new old (cdr l)))))
                 (else (cons (car l) (insertL* new old (cdr l))))))
          (else (cons (insertL* new old (car l)) (insertL* new old (cdr l)))))))


(define member*
  (lambda (a l)
    (cond ((null? l) #f)
          ((atom? (car l))
           (cond ((eq? a (car l)) #t)
                 (else (member* a (cdr l)))))
          (else (or (member* a (car l)) (member* a (cdr l)))))))

           
                  
           
           
                  

              
(rember* 'cup '((coffee) cup ((tea) cup) (and (hick)) cup))
(insertR* 'roast 'chuck '((how much (wood)) could ((a (wood) chuck)) (((chuck))) (if (a) ((wood chuck))) could chuck wood))
(occur* 'banana '((banana) (split ((((banana ice))) (cream (banana)) sherbet))
                           (banana) (bread) (banana brandy)))
(subst* 'orange 'banana '((banana) (split ((((banana ice))) (cream (banana)) sherbet))
                           (banana) (bread) (banana brandy)))
(insertL* 'pecker 'chuck '((how much (wood)) could ((a (wood) chuck)) (((chuck))) (if (a) ((wood chuck))) could chuck wood))
(member* 'chips '((potato) (chips ((with) fish) (chips))))