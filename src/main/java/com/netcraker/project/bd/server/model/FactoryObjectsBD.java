package com.netcraker.project.bd.server.model;

import com.netcraker.project.bd.config.ListenerContext;
import com.netcraker.project.bd.server.model.client.*;
import com.netcraker.project.bd.server.model.location.*;
import com.netcraker.project.bd.server.model.networked.*;
import com.netcraker.project.bd.shared.TypeName;
import com.netcraker.project.bd.shared.containers.SimpleContainer;
import com.netcraker.project.bd.shared.objects.ObjectBD;

import javax.servlet.ServletContext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FactoryObjectsBD {


    private ServletContext context;

    public FactoryObjectsBD(ServletContext context) {
        this.context = context;
    }

    public List<? super ObjectBD> getParent(int id)
    {
        List<? super ObjectBD> ret = new ArrayList<>();

        Connection cn = ListenerContext.getDBOracle(context);
        Statement st;
        try {
            st = cn.createStatement();

            ResultSet rs = st.executeQuery(String.format("SELECT * FROM objects START WITH object_id = %d CONNECT BY object_id = PRIOR container_id  ORDER BY LEVEL DESC", id));

            while (rs.next())
            {
                SimpleContainer<? super ObjectBD> test = getObject(rs.getInt("object_id"),rs.getInt("object_type_id"));

                if(test.getData() == null)
                    continue;
                ret.add(test.getData());
            }
        } catch(SQLException e){
            e.printStackTrace();
            //catch e;
        }

        return ret;
    }

    public List<? super ObjectBD> getChields(int id) {
        List<? super ObjectBD> ret = new ArrayList<>();

        Connection cn = ListenerContext.getDBOracle(context);
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(String.format("SELECT * FROM objects WHERE container_id %s ORDER BY object_type_id,object_id", id == 0 ? "is null" : " = " + id));

            while (rs.next())
            {
                SimpleContainer<? super ObjectBD> test = getObject(rs.getInt("object_id"),rs.getInt("object_type_id"));

                if (null != test.getData()) {
                    ret.add(test.getData());
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return ret;
    }



    public SimpleContainer<? super ObjectBD> getObject(int objectId){
        Connection cn = ListenerContext.getDBOracle(context);
        Statement st;
        SimpleContainer<? super ObjectBD> ret = null;
        try {
            st = cn.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM objects WHERE object_id = " + objectId + "");

            rs.next();
            int objectType = rs.getInt("object_type_id");

            ret = getObject(objectId,objectType);

        } catch(SQLException e){
           e.printStackTrace();
        }

        return ret;
    }



    public SimpleContainer<? super ObjectBD> getObject(int objectId, int type)
    {
        SimpleContainer<? super ObjectBD> ret = new SimpleContainer<>();
        TypeName typeName = TypeName.forType(type);
        switch (typeName){
            case BUILDING://Здание
                ret.setData(new ModelBuilding(context).getById(objectId));
                break;
            case FLOOR://Этаж
                ret.setData(new ModelFloor(context).getById(objectId));
                break;
            case ROOM://Комната
                ret.setData(new ModelRoom(context).getById(objectId));
                break;
            case STAND://Стойка
                ret.setData(new ModelStand(context).getById(objectId));
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
                ret.setData(new ModelCarrier(context).getById(objectId));
                break;
            case SWITCH://Комутатор
                ret.setData(new ModelSwitch(context).getById(objectId));
                break;
            case ROUTER://Маршрутизатор
                ret.setData(new ModelRouter(context).getById(objectId));
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
                ret.setData(new ModelPort(context).getById(objectId));
                break;
            case ORDER://Заказ
                ret.setData(new ModelOrder(context).getById(objectId));
                break;
            case CUSTOMER://Заказчик
                ret.setData(new ModelCustomer(context).getById(objectId));
                break;
            case SERVICE://Услуга(сервис)
                ret.setData(new ModelService(context).getById(objectId));
                break;
            case TARIFF://Тариф
                ret.setData(new ModelTariff(context).getById(objectId));
                break;
            case CSI://Оказание услуги(SCI)
                ret.setData(new ModelCSI(context).getById(objectId));
                break;
            case BILLING://Операции по счёту(billing)
                ret.setData(new ModelBilling(context).getById(objectId));
                break;
            case TSP://Цена (TSP)
                ret.setData(new ModelTSP(context).getById(objectId));
                break;
            case STOCK://Хранилище
                ret.setData(new ModelStock(context).getById(objectId));
                break;
            //case NEW_STAGE://Доп хранилища(для тестироваия)
                ////case CONNECTING://Соеденительное

        }
        return ret;
    }
}
