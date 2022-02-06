-- ошибки в расписании
with intervals as (
    select film_id,
           showtime.id                                                 show_id,
           film.title,
           showtime.time                                               start,
           date_add(showtime.time, interval film.durability minute) as end
    from showtime
             inner join film on showtime.film_id = film.id)
select i1.show_id,
       i2.show_id,
       i1.title,
       i1.start,
       i1.end,
       i2.title,
       i2.start,
       i2.end
from intervals i1
         inner join intervals i2 on i1.start < i2.end
    and i1.end > i2.start
    and i1.show_id != i2.show_id
    and i1.show_id < i2.show_id;

-- общие сборы по убыванию + итого 
with shows as (
    select count(showtime.id) s,
           film_id
    from showtime
             inner join film on showtime.film_id = film.id
    group by film_id)
select film.title                          'Название',
       sum(price)                          'Сборы',
       count(ticket.ticket_id)             'Посетители',
       shows.s                             'Сеансы',
       (count(ticket.ticket_id) / shows.s) 'Среднее число посетителей'
from movies.film
         left outer join movies.showtime on film.id = showtime.film_id
         left outer join movies.ticket on showtime.id = ticket.showtime_id
         left outer join shows on shows.film_id = film.id
group by film.title
with rollup
order by sum(price) < (select sum(price) from ticket) desc, sum(price) desc;

-- группировка по времени
select count(ticket_id) tickets, sum(price) summ, 'between 2022-01-29 09:00:00 and 2022-01-29 15:00:00' period
from ticket inner join showtime on ticket.showtime_id = showtime.id where time between '2022-01-29 09:00:00' and '2022-01-29 15:00:00'
union
select count(ticket_id), sum(price) summ, 'between 2022-01-29 15:00:00 and 2022-01-29 18:00:00'
from ticket inner join showtime on ticket.showtime_id = showtime.id where time between '2022-01-29 15:01:00' and '2022-01-29 18:00:00'
union
select count(ticket_id), sum(price) summ, 'between 2022-01-29 18:00:00 and 2022-01-29 21:00:00'
from ticket inner join showtime on ticket.showtime_id = showtime.id where time between '2022-01-29 18:01:00' and '2022-01-29 21:00:00';