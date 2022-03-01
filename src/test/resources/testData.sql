insert
into
    notice
(id, create_date_time, update_date_time, content, title, end_date, start_date,  view_count, writer)
values
(1, '2022-03-01', '2022-03-01', '공지사항 제목 1', '공지사항 내용 1', '2099-03-02', '2022-03-01', 0, '관리자 1');


insert
into
    attachment_file
(id, create_date_time, update_date_time, file_name, file_url, notice_id)
values
    (1, '2022-03-01', '2022-03-01', '첨부파일 1', 'https://picbel.github.io/1', 1);


insert
into
    attachment_file
(id, create_date_time, update_date_time,file_name, file_url, notice_id)
values
    (2, '2022-03-01', '2022-03-01', '첨부파일 2', 'https://picbel.github.io/2', 1);


insert
into
    notice
(id, create_date_time, update_date_time, title, content, end_date, start_date,view_count, writer)
values
    (2, '2022-03-01', '2022-03-01', '공지사항 제목 2', '공지사항 내용 2', '2099-03-02', '2022-03-01', 0, '관리자 1');