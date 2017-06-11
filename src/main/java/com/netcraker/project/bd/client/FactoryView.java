package com.netcraker.project.bd.client;

import com.google.gwt.user.client.Window;
import com.netcraker.project.bd.client.buttons.AdditionButton;
import com.netcraker.project.bd.client.buttons.PortButtons;
import com.netcraker.project.bd.client.full.CustomerFull;
import com.netcraker.project.bd.client.full.FullWindowPanel;
import com.netcraker.project.bd.client.windows.TreePanel;
import com.netcraker.project.bd.shared.TypeName;
import com.netcraker.project.bd.shared.objects.ObjectBD;
import com.netcraker.project.bd.shared.objects.client.*;
import com.netcraker.project.bd.shared.objects.location.*;
import com.netcraker.project.bd.shared.objects.networked.Carrier;
import com.netcraker.project.bd.shared.objects.networked.Port;
import com.netcraker.project.bd.shared.objects.networked.Router;
import com.netcraker.project.bd.shared.objects.networked.Switch;

import java.util.HashMap;

/**
 * Created by mutat on 08.06.2017.
 */
public class FactoryView {

    static public AdditionButton getAddition(ObjectBD obj, TreePanel tree)
    {
        TypeName name = TypeName.forType(obj.getObjectType());
        AdditionButton ret=null;
        switch (name) {
            /*case BUILDING://Здание
                ret = new;
                break;
            case FLOOR://Этаж
                ret = new;
                break;
            case ROOM://Комната
                ret = new;
                break;
            case STAND://Стойка
                ret = new;
                break;
            ////case EQUIPMENT://Оборудование
            ////case HARDWARE://Железо
            //case MOTHERBOARD://Материнская плата
            //case MEMORY://ОЗУ
            //case CPU://Процессор
            //case GPU://Видеокарта
            //case POWER://Блок питания
            //case HDD://Жёсткий диск
            //case SSD://SSD
            //case PC://ПК
            case CARRIER://Провод Carrier
                ret = new;
                break;
            case SWITCH://Комутатор
                ret = new;
                break;
            case ROUTER://Маршрутизатор
                ret = new;
                break;
            ////case STORAGE://Устройство хранения
            ////case LOCATION://Локация
            //case NETWORK_CARD://Сетевая карта
            ////case NETWORKED://Сетевое оборудование
            //case SERVER://Сервер
            //case SWITCH_NO_CONTROL://Неуправляемый комутатор
            //case SWITCH_CONTORL://Управляемый комутатор
            //case SWITCH_SETTING://настраиваемый комутатор*/
            case PORT://Порт
                ret = new PortButtons(tree);
                break;/*
            case ORDER://Заказ
                ret = new;
                break;
            case CUSTOMER://Заказчик
                ret = new;
                break;
            case SERVICE://Услуга(сервис)
                ret = new;
                break;
            case TARIFF://Тариф
                ret = new;
                break;
            case CSI://Оказание услуги(SCI)
                ret = new;
                break;
            case BILLING://Операции по счёту(billing)
                ret = new;
                break;
            case TSP://Цена (TSP)
                ret = new;
                break;
            case STOCK://Хранилище
                ret = new;
                break;*/
            //case NEW_STAGE://Доп хранилища(для тестироваия)
            ////case CONNECTING://Соеденительное

        }
        return ret;
    }
    static public FullWindowPanel getAddition(ObjectBD obj)
    {
        TypeName name = TypeName.forType(obj.getObjectType());
        FullWindowPanel ret=null;
        switch (name) {
            /*case BUILDING://Здание
                ret = new;
                break;
            case FLOOR://Этаж
                ret = new;
                break;
            case ROOM://Комната
                ret = new;
                break;
            case STAND://Стойка
                ret = new;
                break;
            ////case EQUIPMENT://Оборудование
            ////case HARDWARE://Железо
            //case MOTHERBOARD://Материнская плата
            //case MEMORY://ОЗУ
            //case CPU://Процессор
            //case GPU://Видеокарта
            //case POWER://Блок питания
            //case HDD://Жёсткий диск
            //case SSD://SSD
            //case PC://ПК
            case CARRIER://Провод Carrier
                ret = new;
                break;
            case SWITCH://Комутатор
                ret = new;
                break;
            case ROUTER://Маршрутизатор
                ret = new;
                break;
            ////case STORAGE://Устройство хранения
            ////case LOCATION://Локация
            //case NETWORK_CARD://Сетевая карта
            ////case NETWORKED://Сетевое оборудование
            //case SERVER://Сервер
            //case SWITCH_NO_CONTROL://Неуправляемый комутатор
            //case SWITCH_CONTORL://Управляемый комутатор
            //case SWITCH_SETTING://настраиваемый комутатор
            case PORT://Порт
                ret = new PortButtons(tree);
                break;
            case ORDER://Заказ
                ret = new;
                break;*/
            case CUSTOMER://Заказчик
                ret = new CustomerFull();
                break;/*
            case SERVICE://Услуга(сервис)
                ret = new;
                break;
            case TARIFF://Тариф
                ret = new;
                break;
            case CSI://Оказание услуги(SCI)
                ret = new;
                break;
            case BILLING://Операции по счёту(billing)
                ret = new;
                break;
            case TSP://Цена (TSP)
                ret = new;
                break;
            case STOCK://Хранилище
                ret = new;
                break;*/
            //case NEW_STAGE://Доп хранилища(для тестироваия)
            ////case CONNECTING://Соеденительное

        }
        return ret;
    }

    public static ObjectBD createObject(Object e1) {
        HashMap<String, Object> e = (HashMap<String, Object>) e1;
        try {
            TypeName name = TypeName.forType(Integer.valueOf(e.get("objectType").toString()));
            switch (name)
            {
                case BUILDING://Здание
                    return new Building(e);
                case FLOOR://Этаж
                    return new Floor(e);
                case ROOM://Комната
                    return new Room(e);
                case STAND://Стойка
                    return new Stand(e);
                case CARRIER://Провод Carrier
                    return new Carrier(e);
                case SWITCH://Комутатор
                    return new Switch(e);
                case ROUTER://Маршрутизатор
                    return new Router(e);
                case PORT://Порт
                    return new Port(e);
                case ORDER://Заказ
                    return new Order(e);
                case CUSTOMER://Заказчик
                    return new Customer(e);
                case SERVICE://Услуга(сервис)
                    return new Service(e);
                case TARIFF://Тариф
                    return new Tariff(e);
                case CSI://Оказание услуги(SCI)
                    return new CSI(e);
                case BILLING://Операции по счёту(billing)
                    return new Billing(e);
                case TSP://Цена (TSP)
                    return new TSP(e);
                case STOCK://Хранилище
                    return new Stock(e);
                default:
                    Window.alert(name.toString());
            }
            /*switch (Integer.valueOf(e.get("objectType").toString())) {
                case 1://Здание
                    return new Building(e);
                case 2://Этаж
                    return new Floor(e);
                case 3://Комната
                    return new Room(e);
                case 4://Стойка
                    return new Stand(e);
                case 15://Провод Carrier
                    return new Carrier(e);
                case 16://Комутатор
                    return new Switch(e);
                case 17://Маршрутизатор
                    return new Router(e);
                case 28://Порт
                    return new Port(e);
                case 29://Заказ
                    return new Order(e);
                case 30://Заказчик
                    return new Customer(e);
                case 31://Услуга(сервис)
                    return new Service(e);
                case 32://Тариф
                    return new Tariff(e);
                case 33://Оказание услуги(SCI)
                    return new CSI(e);
                case 34://Операции по счёту(billing)
                    return new Billing(e);
                case 35://Цена (TSP)
                    return new TSP(e);
                case 39://Хранилище
                    return new Stock(e);
                default:
                    Window.alert(e.get("objectType").toString());
            }*/
        } catch (Exception e2) {
        }
        return null;
    }
}
