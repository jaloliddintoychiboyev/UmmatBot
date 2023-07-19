import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Methods extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "http://t.me/UmmatTIme_bot";
    }

    @Override
    public String getBotToken() {
        return "6151590072:AAE72ZLaiAtSZals0DZ2UqXrVKYFLbKYF8w";
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        String client_message = update.getMessage().getText();
        Long client_chatId = update.getMessage().getChatId();
        String userName = update.getMessage().getFrom().getUserName();
        String firstName = update.getMessage().getFrom().getFirstName();
        Dbservice dbservice=new Dbservice();
        String regions[] = {"Guliston","Toshkent","Angren", "Andijon", "Arnasoy",  "Bekobod", "Bishkek", "Boysun", "Buloqboshi", "Burchmulla", "Buxoro", "Gazli",  "Denov", "Dehqonobod",
                "Do'stlik", "Dushanbe", "Jalolobod", "Jambul", "Jizzax", "Jomboy", "Zarafshon", "Zomin", "Kattaqo'rg'on", "Konibodom",
                "Konimex", "Koson", "Kosonsoy", "Marg'ilon", "Mingbuloq", "Muborak", "Navoiy", "Namangan", "Nukus", "Nurota", "Olmaota", "Olot", "Oltiariq",
                "Oltinko'l", "Paxtaobod", "Pop", "Rishton", "Sayram", "Samarqand", "Tallimarjon", "Taxtako'pir", "Termiz", "Tomdi", "Toshxovuz",
                "Turkiston", "Turkmanobod", "To'rtko'l", "Uzunquduq", "Urganch", "Urgut", "O'smat", "Uchtepa", "Uchquduq", "Uchqo'rg'on", "O'sh", "Farg'ona",
                "Xazorasp", "Xiva", "Xonobod", "Xonqa", "Xo'jand", "Xo'jaobod", "Chimboy", "Chimkent", "Chortoq", "Chust", "Shaxrixon", "Sherobod", "Shovot",
                "Shumanay", "Yangibozor", "G'allaorol", "G'uzor", "Qarshi", "Qiziltepa", "Qorako'l", "Qorovulbozor", "Quva", "Qumqo'rg'on", "Qo'ng'irot", "Qo'rg'ontepa", "Qo'qon"};
        if (client_message.equals("/start")) {
            SendMessage sendMessage = new SendMessage();
            try {
                dbservice.addUser(message.getFrom().getFirstName(),message.getFrom().getUserName(), String.valueOf(client_chatId));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            sendMessage.setText("Assalomu Alaykum Xurmatli " + userName + "\n" + "  Xush kelibsiz \uD83E\uDD70" + "\n" + " Namoz vaqtlari \uD83C\uDF19\n  haqida ma'lumot \uD83C\uDF0F\n olishingiz mumkin! ");
            sendMessage.setChatId(client_chatId);
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
            KeyboardRow keyboardRow = new KeyboardRow();
            KeyboardButton button1 = new KeyboardButton();
            button1.setText("Namoz vaqtlari");
            keyboardRow.add(button1);
            keyboardRows.add(keyboardRow);
            replyKeyboardMarkup.setKeyboard(keyboardRows);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (client_message.equals("Namoz vaqtlari")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Hududni Tanlang!");
            sendMessage.setChatId(update.getMessage().getChatId());
            ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setOneTimeKeyboard(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
            //
            KeyboardRow keyboardRow2 = new KeyboardRow();
            KeyboardButton button2 = new KeyboardButton();
            button2.setText("Hududni Izlash");
            keyboardRow2.add(button2);
            keyboardRows.add(keyboardRow2);
            KeyboardRow keyboardRow3 = new KeyboardRow();
            KeyboardButton button3 = new KeyboardButton();
            button3.setText("Admin Haqida");
            keyboardRow3.add(button3);
            keyboardRows.add(keyboardRow3);
            //
            for (int i = 0; i < regions.length; i++) {
                KeyboardRow keyboardRow = new KeyboardRow();
                KeyboardButton button1 = new KeyboardButton();
                button1.setText(regions[i]);
                keyboardRow.add(button1);
                keyboardRows.add(keyboardRow);
            }
            replyKeyboardMarkup.setKeyboard(keyboardRows);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        for (int i = 0; i < regions.length; i++) {
            if (client_message.equals(regions[i]) && client_message != "Admin Haqida" && client_message != "Hududni Izlash") {
                SendMessage sendMessage = new SendMessage();
                Gson gson = new Gson();
                URL url = null;
                try {
                    url = new URL("https://islomapi.uz/api/present/day?region=" + regions[i]);
                    URLConnection urlConnection = url.openConnection();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    NamozTime namozTime = new NamozTime();
                    namozTime = gson.fromJson(bufferedReader, new TypeToken<NamozTime>() {
                    }.getType());
                    String matn = "Informs : ";
                    matn += "\n" + "Hudud : " + namozTime.getRegion();
                    matn += "\n" + "Sana: " + namozTime.getDate()+" ⏱";
                    matn += "\n" + "Hafta kuni: " + namozTime.getWeekday();
                    //  matn += "\n" + namozTime.getTimes();
                    matn+="\n"+"Bomdod Vaqti : "+namozTime.getTimes().getTong_saharlik();
                    matn+="\n"+"Quyosh Vaqti : "+namozTime.getTimes().getQuyosh();
                    matn+="\n"+"Peshin Vaqti : "+namozTime.getTimes().getPeshin();
                    matn+="\n"+"Asr Vaqti : "+namozTime.getTimes().getAsr();
                    matn+="\n"+"Shom Vaqti : "+namozTime.getTimes().getShom_iftor();
                    matn+="\n"+"Hufton Vaqti : "+namozTime.getTimes().getHufton();
                    sendMessage.setText(matn);
                    sendMessage.setChatId(client_chatId);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if (client_message.equals("Admin Haqida")) {
            String text = "Admin: Jaloliddin To'ychiboyev\n\n Telegram :https://t.me/JaloliddinToychiboyev\n\n" +
                    "Instagram : https://instagram.com/jaloliddin.toychiboyev?igshid=MzNlNGNkZWQ4Mg==\n\n" +
                    "LinkedIn : https://www.linkedin.com/in/jaloliddin-to-ychiboyev-a68bb4280?lipi=urn%3Ali%3Apage%3Ad_flagship3_profile_view_base_contact_details%3BcoMzr5LHRFe1bh1uWdv%2FNw%3D%3D\n\n" +
                    "Gmail : jaloliddintoychiboyev5@gmail.com\n\n" +
                    "GitHub : https://github.com/jaloliddintoychiboyev\n\n" +
                    "Phone : +99894 852 1809";
            SendMessage sendMessage1 = new SendMessage();
            sendMessage1.setChatId(client_chatId);
            sendMessage1.setText(text);
            try {
                execute(sendMessage1);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        if (client_message.equals("Hududni Izlash")) {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText("Izlash Uchun Shaharni kiriting!\n Namuna: Toshkent \nNamuna :Guliston\n Iltimos Hudud Nomini To'g'ri Kiriting!\n Aks Holda Ma'lumot Berilmaydi!");
            sendMessage.setChatId(client_chatId);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
