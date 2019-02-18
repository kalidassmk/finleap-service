-- Template data

insert into template (id,template_key, body, status)
  values('1001','WelcomeUserTemplate','Hello dear {{user.salutation}} {{user.name}},\n this are our latest news...\n In case you don{{apostrophe}}}t want to receive any further newsletters in the future please unsubscribe here: \n https://domain-of-product.de/unsubscribe-newsletter/{{user.identifier}}\n  Best Regards,\n Your Customer Support Team','ACTIVE');

insert into template (id,template_key, body, status)
  values('1002','SubscribedUserTemplate','Hello dear {{user.salutation}} {{user.name}},\n we are very happy to welcome you to our newsletter.\n  In case you don{{apostrophe}}t want to receive any further newsletters in the future please unsubscribe here:\n  https://domain-of-product.de/unsubscribe-newsletter/{{user.identifier}} \n  Best Regards, \n  Your Customer Support Team','ACTIVE');

