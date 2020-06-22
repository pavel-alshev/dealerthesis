update AUTODEALER_PURCHASE_REQUEST set IS_PAID = false where IS_PAID is null ;
alter table AUTODEALER_PURCHASE_REQUEST alter column IS_PAID set not null ;
