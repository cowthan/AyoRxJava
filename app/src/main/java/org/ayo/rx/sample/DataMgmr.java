package org.ayo.rx.sample;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/14 0014.
 */


////////////////////////////////////////////////////////////////////
//                            _ooOoo_                             //
//                           o8888888o                            //
//                           88" . "88                            //
//                           (| -_- |)                            //
//                           O\  =  /O                            //
//                        ____/`---'\____                         //
//                      .'  \\|     |//  `.                       //
//                     /  \\|||  :  |||//  \                      //
//                    /  _||||| -:- |||||-  \                     //
//                    |   | \\\  -  /// |   |                     //
//                    | \_|  ''\---/''  |   |                     //
//                    \  .-\__  `-`  ___/-. /                     //
//                  ___`. .'  /--.--\  `. . ___                   //
//                ."" '<  `.___\_<|>_/___.'  >'"".                //
//              | | :  `- \`.;`\ _ /`;.`/ - ` : | |               //
//              \  \ `-.   \_ __\ /__ _/   .-` /  /               //
//        ========`-.____`-.___\_____/___.-`____.-'========       //
//                             `=---='                            //
//        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^      //
//         佛祖保佑       永无BUG        永不修改                    //
////////////////////////////////////////////////////////////////////
public class DataMgmr {

//    public interface Callback{
//        void onFinish(boolean isSuccess, List<String> list, Throwable e);
//    }

    public static class Memory{
        public static List<String> getDataListQuick(){
            List<String> list = new ArrayList<>();
            list.add("01 //      ┏┛ ┻━━━━━┛ ┻┓");
            list.add("02 //      ┃　　　　　　 ┃  ");
            list.add("03 //      ┃　　　━　　　┃  ");
            list.add("04 //      ┃　┳┛　  ┗┳　┃  ");
            list.add("05 //      ┃　　　　　　 ┃  ");
            list.add("06 //      ┃　　　┻　　　┃  ");
            list.add("07 //      ┃　　　　　　 ┃  ");
            list.add("08 //      ┗━┓　　　┏━━━┛  ");
            list.add("09 //        ┃　　　┃   神兽保佑  ");
            list.add("10 //        ┃　　　┃   代码无BUG！  ");
            list.add("11 //        ┃　　　┗━━━━━━━━━┓  ");
            list.add("12 //        ┃　　　　　　　    ┣┓  ");
            list.add("13 //        ┃　　　　         ┏┛  ");
            list.add("14 //        ┗━┓ ┓ ┏━━━┳ ┓ ┏━┛  ");
            list.add("15 //          ┃ ┫ ┫   ┃ ┫ ┫  ");
            list.add("16 //          ┗━┻━┛   ┗━┻━┛  ");
            return list;

        }
    }

    public static class File{

    }

    public static class DB{

    }

    public static class server{

        public static List<String> getDataListSlow(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<String> list = new ArrayList<>();

//            list.add("欲迎还拒羞解衣");
//            list.add("半似游客半似鸡");
//            list.add("身无分文行千里");
//            list.add("别人刷卡我刷逼");
//            list.add(".....");
            list.add("01 ////////////////////////////////////////////////////////////////////");
            list.add("02 //                            _ooOoo_                             //");
            list.add("03 //                           o8888888o                            //");
            list.add("04 //                           88\" . \"88                            //");
            list.add("05 //                           (| -_- |)                            //");
            list.add("06 //                           O\\  =  /O                            //");
            list.add("07 //                        ____/`---'\\____                         //");
            list.add("08 //                      .'  \\\\|     |//  `.                       //");
            list.add("09 //                     /  \\\\|||  :  |||//  \\                      //");
            list.add("10 //                    /  _||||| -:- |||||-  \\                     //");
            list.add("11 //                    |   | \\\\\\  -  /// |   |                     //");
            list.add("12 //                    | \\_|  ''\\---/''  |   |                     //");
            list.add("13 //                    \\  .-\\__  `-`  ___/-. /                     //");
            list.add("14 //                  ___`. .'  /--.--\\  `. . ___                   //");
            list.add("15 //                .\"\" '<  `.___\\_<|>_/___.'  >'\"\".                //");
            list.add("16 //              | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |               //");
            list.add("17 //              \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /               //");
            list.add("18 //        ========`-.____`-.___\\_____/___.-`____.-'========       //");
            list.add("19 //                             `=---='                            //");
            list.add("20 //        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^      //");
            list.add("21 //         佛祖保佑       永无BUG        永不修改                    //");
            list.add("22 ////////////////////////////////////////////////////////////////////");
            return list;

        }

    }

}
