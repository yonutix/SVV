/*
    // install nodemailer first
    npm install nodemailer
*/

var nodemailer = require('nodemailer');

// create reusable transporter object using the default SMTP transport
var transporter = nodemailer.createTransport('smtps://bookeat101%40gmail.com:bookeat102@smtp.gmail.com');

// setup e-mail data with unicode symbols
var mailOptions = {
    from: 'bookeat101@gmail.com', // sender address
    to: 'emil.racec@gmail.com', // list of receivers
    subject: 'Hello ✔', // Subject line
    text: 'BookEat Reservation', // plaintext body
    html: '<b>Have a great day!</b>', // html body
    attachments: [
        {   // filename and content type is derived from path
            path: 'BookEatLogo.jpg'
        },]
};

// send mail with defined transport object
transporter.sendMail(mailOptions, function(error, info){
    if(error){
        return console.log(error);
    }
    console.log('Message sent: ' + info.response);
});