create or replace TRIGGER ins_eve_participants
  AFTER INSERT ON eve_participants
  For each row
DECLARE
 PRAGMA AUTONOMOUS_TRANSACTION;
  v_event eve_events%ROWTYPE;
  v_all_participants integer;
BEGIN
  select * into v_event from eve_events where id_event = :new.id_event;
    DBMS_OUTPUT.put_line(v_event.capacity);
  select count(1) into v_all_participants from eve_participants where id_event = :new.id_event;
  DBMS_OUTPUT.put_line(v_all_participants);
  if v_event.capacity <= v_all_participants +1 then
    update eve_events set register_enabled = 0 where id_event=v_event.id_event; 
    DBMS_OUTPUT.put_line('jestem');
  end if;
   commit;
END;
--drop trigger INS_EVE_PARTICIPANTS;
/
create or replace TRIGGER ins_eve_participants_before
  BEFORE INSERT ON eve_participants
  For each row
DECLARE
    e_register_is_not_enabled exception;
    e_email_is_not_uniqe exception;
    v_event eve_events%ROWTYPE;
  BEGIN
    select * into v_event from eve_events where id_event = :new.id_event;
    if v_event.register_enabled = 0 then
        raise e_register_is_not_enabled;
    end if;
    
    for i in (select null from eve_participants where id_event=:new.id_event and email=:new.email) loop
        raise e_email_is_not_uniqe;
    end loop;
  END;