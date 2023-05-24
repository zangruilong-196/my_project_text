package manger;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import classes.User;
import classes.Work;
import classes.pinglun;
import gongju.Dictionaries;
import gongju.PersistenceUtils;
import gongju.paixu;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;



public class WorksManger {


    public static void remen(int[] a)
    {
        for(int k = 0;k<a.length-1;k++)
        {
            for(int j = 0;j<a.length-1-k;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }

        int[] temp = new int[a.length];
        //把原数组的内容反转后赋值给数组temp
        for (int i=0;i<a.length;i++){
            temp[i] = a[a.length-i-1];
        }
        //由于要求是对原数组array实现反转效果，所以再把temp挨个赋值给array
        for (int i=0;i<temp.length;i++)
        {
            a[i] = temp[i];
        }

        int x =1;
        for (int m:a)
        {

            for(Work oneWork:Dictionaries.worksList)
            {
                if(oneWork.getCount()==m)
                {
                    System.out.println("top"+x+"\t"+"作者"+oneWork.getUserName()+"\t"+"获赞:\t"+oneWork.getCount());
                    break;
                }

            }
            x = x+1;
            if(x==11)
            {
                break;
            }
        }


    }

    public static void paixu(int[] a)
    {
        for(int k = 0;k<a.length-1;k++)
        {
            for(int j = 0;j<a.length-1-k;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }

        int[] temp = new int[a.length];
        //把原数组的内容反转后赋值给数组temp
        for (int i=0;i<a.length;i++){
            temp[i] = a[a.length-i-1];
        }
        //由于要求是对原数组array实现反转效果，所以再把temp挨个赋值给array
        for (int i=0;i<temp.length;i++)
        {
            a[i] = temp[i];
        }

        int x =1;
        for (int m:a)
        {

            for(Work oneWork:Dictionaries.worksList)
            {
                if(oneWork.getCount()==m)
                {
                    System.out.println("top"+x+"\t"+"id:"+oneWork.getId()+"\t"+"书名："+oneWork.getName()+"\t"+"获赞:\t"+oneWork.getCount());
                    break;
                }

            }
            x = x+1;
            if(x==11)
            {
                break;
            }
        }


    }

    public static void paixu1(int[] a)
    {
        for(int k = 0;k<a.length-1;k++)
        {
            for(int j = 0;j<a.length-1-k;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }

        int[] temp = new int[a.length];
        //把原数组的内容反转后赋值给数组temp
        for (int i=0;i<a.length;i++){
            temp[i] = a[a.length-i-1];
        }
        //由于要求是对原数组array实现反转效果，所以再把temp挨个赋值给array
        for (int i=0;i<temp.length;i++)
        {
            a[i] = temp[i];
        }

        int x =1;
        for (int m:a)
        {

            for(Work oneWork:Dictionaries.worksList)
            {
                if(oneWork.getCount()==m)
                {
                    System.out.println("top"+x+"\t"+"id:"+oneWork.getId()+"\t"+"书名："+oneWork.getName()+"\t"+"获赞:\t"+oneWork.getCount());
                    break;
                }

            }
            x = x+1;
        }


    }


    public static void pahang1(int[] a)
    {
        for(int k = 0;k<a.length-1;k++)
        {
            for(int j = 0;j<a.length-1-k;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }

        int[] temp = new int[a.length];
        //把原数组的内容反转后赋值给数组temp
        for (int i=0;i<a.length;i++){
            temp[i] = a[a.length-i-1];
        }
        //由于要求是对原数组array实现反转效果，所以再把temp挨个赋值给array
        for (int i=0;i<temp.length;i++)
        {
            a[i] = temp[i];
        }


        int x =1;
        for (int m:a)
        {
            for(Work oneWork:Dictionaries.worksList)
            {
                if(oneWork.getCount()==m && oneWork.getType()==1)
                {
                    System.out.println("top"+x+"\t"+"id:"+oneWork.getId()+"\t"+"书名："+oneWork.getName()+"\t"+"获赞:\t"+oneWork.getCount());
                    x++;
                }

            }
        }


    }

    public static void pahang2(int[] a)
    {
        for(int k = 0;k<a.length-1;k++)
        {
            for(int j = 0;j<a.length-1-k;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }

        int[] temp = new int[a.length];
        //把原数组的内容反转后赋值给数组temp
        for (int i=0;i<a.length;i++){
            temp[i] = a[a.length-i-1];
        }
        //由于要求是对原数组array实现反转效果，所以再把temp挨个赋值给array
        for (int i=0;i<temp.length;i++)
        {
            a[i] = temp[i];
        }


        int x =1;
        for (int m:a)
        {
            for(Work oneWork:Dictionaries.worksList)
            {
                if(oneWork.getCount()==m && oneWork.getType()==2)
                {
                    System.out.println("top"+x+"\t"+"id:"+oneWork.getId()+"\t"+"书名："+oneWork.getName()+"\t"+"获赞:\t"+oneWork.getCount());
                    x++;
                }

            }
        }


    }

    public static void pahang3(int[] a)
    {
        for(int k = 0;k<a.length-1;k++)
        {
            for(int j = 0;j<a.length-1-k;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }

        int[] temp = new int[a.length];
        //把原数组的内容反转后赋值给数组temp
        for (int i=0;i<a.length;i++){
            temp[i] = a[a.length-i-1];
        }
        //由于要求是对原数组array实现反转效果，所以再把temp挨个赋值给array
        for (int i=0;i<temp.length;i++)
        {
            a[i] = temp[i];
        }


        int x =1;
        for (int m:a)
        {
            for(Work oneWork:Dictionaries.worksList)
            {
                if(oneWork.getCount()==m && oneWork.getType()==3)
                {
                    System.out.println("top"+x+"\t"+"id:"+oneWork.getId()+"\t"+"书名："+oneWork.getName()+"\t"+"获赞:\t"+oneWork.getCount());
                    x++;
                }

            }
        }


    }

    public static void pahang4(int[] a)
    {
        for(int k = 0;k<a.length-1;k++)
        {
            for(int j = 0;j<a.length-1-k;j++)
            {
                if(a[j]>a[j+1])
                {
                    int temp = a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }

        int[] temp = new int[a.length];
        //把原数组的内容反转后赋值给数组temp
        for (int i=0;i<a.length;i++){
            temp[i] = a[a.length-i-1];
        }
        //由于要求是对原数组array实现反转效果，所以再把temp挨个赋值给array
        for (int i=0;i<temp.length;i++)
        {
            a[i] = temp[i];
        }


        int x =1;
        for (int m:a)
        {
            for(Work oneWork:Dictionaries.worksList)
            {
                if(oneWork.getCount()==m && oneWork.getType()==4)
                {
                    System.out.println("top"+x+"\t"+"id:"+oneWork.getId()+"\t"+"书名："+oneWork.getName()+"\t"+"获赞:\t"+oneWork.getCount());
                    x++;
                }

            }
        }


    }

    /**
     * 功能：Java读取txt文件的内容
     * 步骤：1：先获得文件句柄
     * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
     * 3：读取到输入流后，需要读取生成字节流
     * 4：一行一行的输出。readline()。
     * 备注：需要考虑的是异常情况
     * @param filePath
     */
    public static void readTxtFile(String filePath) {
        try {
            String encoding = "UTF-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }




    public void showMenu(User loginUser){


        Work w1 = new Work("1","诛仙","该小说以“天地不仁，以万物为刍狗”为主题，\n" +
                "讲述了青云山下的普通少年张小凡的成长经历故事",31124,1,"萧鼎",3);

        Work w2 = new Work("2","遮天","本书以九龙拉棺为引子，带出一个庞大的洪荒玄幻世界，\n" +
                "引出上古神话的遗秘，卷帙浩阔，设定繁杂，人物众多",21124,1,"辰东",4);

        Work w3 = new Work("3","择天记","作品讲述了在人妖魔共存的架空世界里，\n" +
                "陈长生为了逆天改命，带着一纸婚书来到神都，与一群少年英雄伙伴并肩与黑暗势力展开了殊死斗争，\n" +
                "同时也收获了爱情，在神都开启一个逆天强者的崛起征程。",21324,1,"猫腻",7);

        Work w4 = new Work("4","完美世界","一粒尘可填海，一根草斩尽日月星辰，弹指间天翻地覆。\n" +
                "群雄并起，万族林立，诸圣争霸，乱天动地；问苍茫大地，谁主沉浮？\n" +
                "一个少年从大荒中走出，一切从这里开始。",66443,1,"辰东",4);

        Work w5 = new Work("5","剑来","大千世界，无奇不有。\n" +
                "天道崩塌，我陈平安，唯有一剑，\n" +
                "可搬山，断江，倒海，降妖，镇魔，敕神，摘星，摧城，开天。",95345,1,"烽火戏诸侯",4);

        Work w6 = new Work("6","剑王朝","一个出身毫无疑问的秦国都长陵普通的市井少年，\n" +
                "每天所想的，却是颠覆大秦王朝，杀死修行已至前所未有的第八境的秦皇帝。",36894,1,"无罪",9);

        Work w7 = new Work("7","凡人修仙传","小说讲述了一个普通的山村穷小子，\n" +
                "偶然之下，跨入到一个江湖小门派，虽然资质平庸，但依靠自身努力和合理算计最后修炼成仙的故事。",2674,1,"忘语",6);

        Work w8 = new Work("8","雪中悍刀行","该小说讲述一个关于庙堂权争与刀剑交错的时代，一个暗潮涌动粉墨登场的江湖。\n" +
                "荣获首届网络文学双年奖之银奖作品。",34567,1,"烽火戏诸侯",4);

        Work w9 = new Work("9","牧神记","大墟的祖训说，天黑，别出门。\n" +
                "大墟残老村的老弱病残们从江边捡到了一个婴儿，取名秦牧，含辛茹苦将他养大。这一天夜幕降临，黑暗笼罩大墟，秦牧走出了家门……\n" +
                "做个春风中荡漾的反派吧！\n" +
                "瞎子对他说。\n" +
                "秦牧的反派之路，正在崛",23344,1,"宅猪",9);

        Work w10 = new Work("10","斗破苍穹","讲述了天才少年萧炎在创造了家族空前绝后的修炼纪录后突然成了废人，种种打击接踵而至。\n" +
                "就在他即将绝望的时候，一缕灵魂从他手上的戒指里浮现，一扇全新的大门在面前开启，\n" +
                "经过艰苦修炼最终成就辉煌的故事。",32778,1,"天蚕土豆",3);

        Work w11 = new Work("11","天龙八部","这部小说以宋哲宗时代为背景，通过宋、辽、大理、西夏、吐蕃等王国之间的武林恩怨和民族矛盾，\n" +
                "从哲学的高度对人生和社会进行审视和描写，展示了一幅波澜壮阔的生活画卷。\n" +
                "其故事之离奇曲折、涉及人物之众多、历史背景之广泛、武侠战役之庞大、想象力之丰富当属“金书”之最。\n" +
                "作品风格宏伟悲壮，是一部写尽人性、悲剧色彩浓厚的史诗巨著。",8664,2,"金庸",9);

        Work w12 = new Work("12","射雕英雄传","以宋宁宗庆元五年（1199年）至成吉思汗逝世（1227年）这段历史为背景，\n" +
                "反映了南宋抵抗金国与蒙古两大强敌的斗争，充满爱国的民族主义情愫。",89004,2,"金庸",4);


        Work w13 = new Work("13","神雕侠侣","“射雕三部曲”系列的第二部,\n" +
                "小说的主脉写的是杨康之遗孤杨过与其师小龙女之间的爱情故事。\n" +
                "杨过14岁起师从18岁的小龙女，于古墓派之中苦练武功，师徒二人情深义重、日久生情，却无奈于江湖阴鸷险恶、蒙古铁骑来犯使得有情之人难成眷属。\n" +
                "历经一番坎坷与磨难的考验，杨过冲破封建礼教之禁锢，最终与小龙女由师徒变为“侠侣”。\n" +
                "同时，在这段磨难经历中，杨过也消除了对郭靖、黄蓉夫妇的误会，在家仇与国难间作出抉择，成为真正的“侠之大者”。",6788,2,"金庸",9);

        Work w14 = new Work("14","倚天屠龙记","以安徽农民朱元璋揭竿而起建立明朝天下为背景，\n" +
                "以张无忌的成长为线索，叙写江湖上的各帮各派、各种人物的恩怨情仇，\n" +
                "它把中国历史上元朝的兴衰和江湖道义、恩仇平行交叉起来。\n" +
                "该书表达了作者既反对异族侵略，也反对本民族暴政的思想。",3888,2,"金庸",9);

        Work w15 = new Work("15","笑傲江湖","通过叙述华山派大弟子令狐冲的江湖经历，反映了武林各派争霸夺权的历程。\n" +
                "作品没有设置时代背景，“类似的情景可以发生在任何朝代”，折射出中国人独特的政治斗争状态，同时也表露出对斗争的哀叹，具有一定的政治寓意。\n" +
                "小说情节跌宕起伏，波谲云诡，人物形象个性鲜明，生动可感。",362788,2,"金庸",8);

        Work w16 = new Work("16","侠客行","叙述一个懵懂少年石破天的江湖经历.\n" +
                "实际上是写人无法主宰自己的命运，小说用多条线索，从不同的层面上展示了这一主题。",3188,2,"金庸",9);


        Work w17 = new Work("17","剑客行","古龙早期的作品，\n" +
                "讲述少年展白背负父亲“霹雷剑”展云天被五个结义兄弟\n" +
                "以奸计密谋杀害的血海深仇，闯荡江湖，学成绝世武功，终报父仇，\n" +
                "并独力对抗企图一统中原武林的强大恶势力“南海门”，\n" +
                "除“三煞”，诛“四凶”，力挽狂澜，创下赫赫声名，\n" +
                "最终与五个美艳动人的娇妻隐居洞庭湖畔，成就武林传奇，一时传为佳话。",21374,2,"古龙",4);

        Work w18 = new Work("18","楚留香传奇","本书主要讲述楚留香和他的朋友们的传奇故事。\n" +
                "古龙曾经在《楚留香新传》的前言中说道：\n" +
                "「楚留香一生中实在是多彩多姿，充满了传奇性。只要是有关他的故事，就一定充满了不平凡的刺激。」",9724,2,"古龙",3);

        Work w19 = new Work("19","边城浪子","“关东刀马，天下无双”。\n" +
                "位于关东边城的“神刀堂”其堂主白天羽惊才绝艳，武功盖世，\n" +
                "与魔教教主曾于天山立约赌技大败魔教，令魔教从此不敢入关骚扰中原，神刀堂因此名声大噪，其武林威望远超同处关东边城的“万马堂”，\n" +
                "却不料遭到了结义兄弟“万马堂”堂主马空群的嫉妒。\n" +
                "于是马空群摆下宴席，邀约白天羽夫妇携全家老小一同去梅花庵赴宴赏雪，\n" +
                "谁知却遭遇三十名蒙面刺客埋伏暗杀，梅花庵外尸横遍野，白家人和刺客们的鲜血将白色的雪染成红色，白家上下十一口全部遇难无一幸存，\n" +
                "此事件成为一桩悬案，震惊整个江湖，十八年来江湖上依然没有人知道参与此次血案的凶手们是谁。\n",308184,2,"古龙",7);

        Work w20 = new Work("20","天涯·明月·刀","《天涯·明月·刀》的故事是古龙另一作品《边城浪子》的后传，\n" +
                "书中的主角傅红雪已是中年人。\n" +
                "故事讲述了一个在江湖中占有着巨大财富和无上权威的名侠，同时也是武林之首的公子羽，\n" +
                "以名利财富诱惑天下第一剑燕南飞做自己的替身，并派遣他和手下明月心前去刺杀傅红雪。\n" +
                "傅红雪两次战胜燕南飞却没有杀死他。\n" +
                "公子羽还制造出关于天下第一暗器孔雀翎的传说，江湖人士受诱惑为争夺这件武器而纷纷丧生。\n" +
                "他派自己的手下卓玉贞当了孔雀翎主人秋水清的情妇，继而血洗孔雀山庄。\n" +
                "不明真相的秋水清临死前将卓玉贞托付给傅红雪，但傅红雪从卓玉贞的行径中认识到她的真相。\n" +
                "他怀着坚忍不拔的毅力，杀死公子羽派来的一个个杀手，最后来到公子羽的住处，在决斗中杀死了替代公子羽出战的燕南飞。\n" +
                "他拒绝学燕南飞一样担任公子羽替身，离开那些诱人的财富，\n" +
                "回到一个真心爱他的女人身边。而公子羽也意识到继续下去是不行的，便放弃了名利，退隐江湖",31114,2,"古龙",3);


        Work w21 = new Work("21","三少爷的剑","神剑山庄三少爷谢晓峰10多年来历经上千场大战未尝一败，被天下人尊为“剑神”。\n" +
                "然而，传奇剑客燕十三一直以谢晓峰为目标，苦修剑道，终于在生死边缘悟出惊天地泣鬼神的“夺命十三剑”。\n" +
                "一时之间，江湖沸腾，然而，就在燕十三赶到神剑山庄下战书的时候，迎接他的，却是三少爷谢晓峰的灵柩。\n" +
                "燕十三因痛失对手大感失望，此时神秘女子慕容秋荻出现并告知燕十三，谢晓峰并没有死，\n" +
                "要想找到他决战，燕十三就必须要替她杀一个人",3789,2,"古龙",6);

        Work w22 = new Work("22","听雪楼","听雪楼系列:\n" +
                "《血薇》（已出漫画版,新版含《指间砂》）\n" +
                "《护花铃》（又名《拜月教之战》）\n" +
                "《荒原雪》《指间砂》（已出漫画版）\n" +
                "《忘川》",38900,2,"沧月",6);

        Work w23 = new Work("23","艳杀天下","艳冠后宫的挽月夫人病逝，曾集万千宠爱于一身的她跌落云间，一笑倾君的傲人封号从此是笑资……\n" +
                "敛锋芒、避世故，换来了黄泉路上由死向生的爱恨纠缠，唤出了一笑倾君的倾国之怨。\n" +
                "“父皇，今后儿臣无法常侍左右，父皇一定要保重身体。您一定要好好活着！活着看我回来！我——会回来的！”\n" +
                "更名、改姓、隐市，谋划于五国深宫，步步为营、虚与委蛇……\n" +
                "然，“背叛”两字却在那个眸中深埋悲凉的男子身上消了声隐了迹。\n" +
                "她出口的声音里带着轻柔的魅惑：“倾你一国，换我一命，如何？”\n" +
                "他轻笑道：“这世上还会有第二个晏倾君，却只有一只‘母狐狸’。”\n" +
                "终是为了红颜倾了天下……\n" +
                "笑暖影凉执剑天下俯首，繁花落尽葬毙浮生荣华。",652113,3,"西西东东",6);

        Work w24 = new Work("24","三生三世十里桃花","远古众神凋零，现今只存了龙族、凤族、九尾白狐。\n" +
                "狐帝白止膝下得了四个儿子一个女儿。\n" +
                "这唯一的一个女儿长得颇好，却是个炮灰命。活到十四万岁，共遇得五朵桃花。\n" +
                "一朵碍于异族不能通婚，那思慕尚处于萌芽期，便被该桃花的爹娘终结了。\n" +
                "一朵误以为她是个男儿身，纠结于这段断袖情，待出现个跟她长得相似的女子，立刻便跟着人跑了。\n" +
                "一朵是他爹娘亲自做主给她定的亲，待到他们家走一趟，却看上了她的婢女，两人私奔了。\n" +
                "一朵在心底里暗恋她暗恋了万儿八千年不敢表白，待鼓起勇气来表白时，她前未婚夫的爹娘为了补偿她，又与她重新结了一门亲。\n" +
                "前头四朵桃花有三朵都是烂桃花，唯一算得上好的一朵，却又是个才打骨苞儿的。\n" +
                "这五朵桃花中的最后一朵，是她命中注定的夫君，九重天上的太子夜华。恩怨纠葛如浮云过，她遗憾没在最好的年华里遇上他",316464,3,"唐七公子",5);

        Work w25 = new Work("25","香蜜沉沉烬如霜","一颗匪夷所思的葡萄美人，一只烧焦的凤凰男，一条闪亮的美男鱼。\n" +
                "外加一粒领衔客串的绝情丹。\n" +
                "呃，其实，双修它是一门值得深入探讨的行为艺术。\n" +
                "花开了，窗亦开了，却为何看不见你\n" +
                "看得见你，听得见你，却不能说爱你\n" +
                "真的有来世吗\n" +
                "那么，吾愿为一只振翅的蝶\n" +
                "一滴透纸将散的墨\n" +
                "一粒风化远去的沙",34567,3,"电线",7);

        Work w26 = new Work("26","微微一笑很倾城","该小说讲述了A大计算机系花贝微微与同校师哥肖奈在游戏《梦游江湖》中结伴成亲，\n" +
                "并在现实中相恋相守的故事。\n" +
                "小说通过甜蜜而又曲折的情节极尽对真爱的歌颂，\n" +
                "重新塑造了普通人对爱的期待和信任。\n",51420,3,"顾漫",6);

        Work w27 = new Work("27","招摇","女主负责打打杀杀，男主负责看门保家\n " +
                "人气仙侠言情作家九鹭非香倾情力作\n " +
                "年度不容错过的超好看虐恋暖萌仙侠小说\n" +
                "当年的我挡在了墨青的面前，只身与十大世家斗了一场。\n" +
                " 后人传那次斗法令天地昏暗、江湖枯竭。\n" +
                " 我一身是血地救出了墨青，从此名声外传，\n" +
                "所有人都知道尘稷山出了一个可以单挑十大世家的女魔头。\n" +
                " 之后我就很少听到墨青的消息了，直到我死前才再次看见他。\n" +
                " 我死的那天，正是上古魔器万钧剑重现于世之时……",23456,3,"九鹭非香",7);

        Work w28 = new Work("28","扶摇皇后","考古界“红发魔女”挖墓挖得动静太大，墓室坍塌光荣做了烈士。\n" +
                "十七年后，穿越到五洲大陆、在底层挣扎的混混人士孟扶摇，一刀撩开即将另娶他人的心上人的五指。\n" +
                "“相信我，她会是个十全十美的夫人，你带着她，就像贵妇牵着贵宾犬，到哪里都身价百倍，相得益彰。”\n" +
                "不忠所爱，弃如狗屎。\n" +
                "从此后海阔天空，跋涉万里，夺七国令，争天下先，为了心底回归的信念，与七国权谋皇室悍然碰撞，同天下英才逸士际会风云。\n" +
                "而这一路相逢的爱情，是苍山之巅温暖的篝火、是刀光剑影清冷的回眸、是秋日金风飞掠的衣袖，还是冷月深林如箭的长奔？当爱情与抉择狭路相逢，谁胜？\n" +
                "她说，我能献给你，不过这一身热血，你若不要，我只好放你的血。\n" +
                "她说，我一生的所有努力，都在与真爱背道而驰，天意弄人是么？那我就只好弄天吧。\n" +
                "裂帛三尺，溅血一丈，扩疆千里，横尸万计。\n" +
                "鸾凤一日同风起，扶摇直上，九万里。",54180,3,"天下归元",5);

        Work w29 = new Work("29","杉杉来吃","这就是一部小职员杉杉在大Boss封腾的磨牙霍霍下，\n" +
                "屡战屡败，屡败屡战，\n" +
                "最后不得不乖乖弃械投降的斗争史……\n" +
                "杉杉无比乖顺地蹲在产房前当临时血库，\n" +
                "其间又被大老板支使着去做了个血液检查，以证明身体健康，血液合格。\n" +
                "生产中产妇果然一度危急，杉杉乖乖地被抽了300CC血，\n" +
                "产妇转危为安,\n" +
                "杉杉在言清的千恩万谢下走出了医院，\n" +
                "走了一会，停下，看着月亮仰天长叹...",453452,3,"顾漫",9);

        Work w30 = new Work("30","魔道祖师","小说讲述了因修鬼道被反噬的魏无羡重生之后与旧时蓝忘机一起，\n" +
                "以一只有神志的断手为线索，追踪出了前世今生的一系列谜团的故事。\n" +
                "小说以插叙的手法，回忆线与当前时空交叉并行，\n" +
                "既有主线悬疑剧情的层层深入，又有义城副本的暂离唏嘘。全文有糖有刀、文笔轻松有趣。\n" +
                "故事时间线长、人物关系复杂，其中穿插的回忆杀又使人物形象饱满生动。",36890,3,"墨香铜臭",8);

        Work w31 = new Work("31","花千骨","瑶池初见，他是高高在上的长留上仙，而她偷偷混入，变作小虫趴在树上，却被风吹\n" +
                "落于他的酒盏之中。\n" +
                "“不小心掉下来了吗？”\n" +
                "他的笑淡然而又慈悲，那是她此生唯一一次见到，却是对着一条小虫。\n" +
                "一年之约，拼尽全力，只为了有一天，能叫他一声\"师父\"。\n" +
                "“师父，你为什么收我为徒？”\n" +
                "他不语，只是将宫铃赠予她，轻抚她的头。\n" +
                "那漫天绯色中白得尘埃不染的身影，每日站在绝情殿的露风石上，俯瞰天下苍生。\n" +
                "她发誓说，再也不会让他寂寞了。可是绝情殿上的朝夕相伴，默然相守，终于还是走到了尽头。\n" +
                "为了救他，她犯下弥天大错。然而……\n" +
                "“错了就是错了。”他淡漠依旧。\n" +
                "八十一根消魂钉，还有高高举起的断念剑。\n" +
                "剑断念，人断情......",999890,3,"Fresh果果",4);

        Work w32 = new Work("32","忘川茶舍","奈何之下，黄泉之上，有河名忘川，吸食无数灵魂之情欲，化作忘川灵主流笙。\n" +
                "流笙与司战星君沧陌相爱，却因为误会，沧陌神魄尽散，忘川河水逆流，天下大乱。\n" +
                "流笙为换回爱人神魄，来到人间，以开茶舍为名收集人间最刻骨铭心的感情，每收集到一段如清水般透彻的情感，便将其倒入忘川河内，\n" +
                "当浑浊的忘川河变得清澈时，她就可以再见到她的爱人……\n" +
                "比三生三世还长的等待，比千世轮回更虐心的绝世宿命。\n" +
                "不管尘世几道轮回，忘川总有人爱你如初。\n" +
                "一杯茶，一段情，忘川茶舍，让你不再为情所困。",372113,4,"简小扇",8);

        Work w33 = new Work("33","百灵潭","这是一个有关百灵潭百鬼群妖的情感系列故事，\n" +
                "每篇故事的主人公都有自己独特的情感经历。\n" +
                "它讲述是人与百鬼妖怪之间的情感交织和纠葛。\n" +
                "整个系列弥漫一种淡然平和，即使是怨恨和执念，\n" +
                "最后都能找到最合理的结局。\n" +
                "作者用唯美的笔调娓娓道来，带着特殊的韵味，隽永，灵秀，智慧。\n",334564,4,"吾玉",9);

        Work w34 = new Work("34","红颜手札","她能算天机，算国运，算皇朝兴替，算世事浮沉，\n" +
                "却唯独算不出，那一日西郊纵马，暮色四合，他搂她在怀时的那一瞬，有晚霞有长风有木香，却究竟有没有一丝情？\n" +
                "九兮宫里，君不怜紧了紧斗篷，一张雪白的小脸对着醉颜微醺的莫小玉，\n" +
                "缓缓开口，一字一句：“大渝来犯，你大哥在前线殊死奋战，你却在深宫之中寻欢作乐。莫家世代将门，忠勇无敌，不知九泉之下的莫老将军此时会做何感想？”\n" +
                " 这话一出，殿中气氛陡变，莫小玉混沌的眼眸登时睁大，一扫酒鬼之状，一脚踢翻案几....",34567,4,"吾玉",9);

        Work w35 = new Work("35","哑舍","这是一本讲述古董故事的书，围绕一间名为“哑舍”的古董店展开，\n" +
                "将一件件默然千年的古董赋予灵魂，讲述古物背后的历史，描绘历史背后的人生。\n" +
                "也讲述一位活了两千多年的老板，在历史长河中寻找自己当年追随的太子殿下的故事",54678,4,"玄色",9);

        Work w36 = new Work("36","凤楼","以女为尊的大楚有一座传奇小倌馆凤楼，\n" +
                "里面有各种绝色小倌，\n" +
                "他们或者武力超群，或者有绝色之姿，或者身世特别，因为各种各样的原因进入凤楼，为楼主沈夜所用。\n" +
                "本书由十六个绝色小倌的故事组成，他们集结在一起，\n" +
                "通过凤楼组成一个情报机构，终目的是推翻大楚女尊制度，绝地逆袭",23456,4," 熄歌",9);

        Work w37 = new Work("37","百鬼集","《百鬼集》每个人心中都住着一只恶鬼。\n" +
                "她叫白鬼，她有一支笔，带着一个飘渺的愿望，穿梭在不同的空间之中。记录着许多“鬼”的风花雪月……\n" +
                "女主独白：白鬼，既是我的名，也是我手中这支笔的名。\n" +
                "收齐一百只鬼后，它将替我圆一场千年遗梦。\n" +
                "俗世沉浮，岁月荏苒，我不知穿梭过多少时空，看过多少悲欢离合，渐渐忘了故人，\n" +
                "没了情感，只是心中那个夙愿从未改变……",54378,4,"九鹭非香",9);

        Work w38 = new Work("38","焚舟纪","以童话、民间故事、文学经典为蓝本，\n" +
                "文学女巫卡特以奇绝想象力和非凡叙事技巧将之加以戏仿、混酿 、改装和重塑，\n" +
                "并以通透戏谑的视角呈现出童话背后的冷僻真相，传奇之中的幽暗细节，\n" +
                "为幻想世界打上现实投影，\n" +
                "极具颠覆性却又不损奇幻之美，慑人之余又令人迷醉，\n" +
                "形成融魔幻现实主义、女性主义、哥特风格和寓言色彩为一体的独特写作模式。",7852,4,"安吉拉•卡特",4);
















        //评论
        pinglun p1 = new pinglun("1","2020.10.1","诛仙","zrl", "九尾狐已经忘了狐岐山崩塌有多久了，\n" +
                "只记得那时候，自己不过还是只人形都修不成的小妖。\n" +
                "那时候的狐岐山还是个很美的地方，人妖齐聚，和谐共生。\n");
        pinglun p2 = new pinglun("2","2020.10.2","遮天","zrl","仙之颠，傲世间，有我安澜便是天！ 俞陀救我!");






        //存储书籍
        Dictionaries.worksList.add(w1);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w2);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w3);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w4);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w5);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w6);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w7);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w8);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w9);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w10);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w11);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w12);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w13);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w14);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w15);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w16);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w17);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w18);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w19);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w20);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w21);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w22);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w23);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w24);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w25);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w26);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w27);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w28);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w29);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w30);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w31);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w32);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w33);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w34);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w35);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w36);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w37);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);
        Dictionaries.worksList.add(w38);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\work.data",Dictionaries.worksList);












        //存储评论
        Dictionaries.pinglunlist.add(p1);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\pinglun.data",Dictionaries.pinglunlist);
        Dictionaries.pinglunlist.add(p2);
        PersistenceUtils.dataPersistence("D:\\javadatatext\\pinglun.data",Dictionaries.pinglunlist);





        while(true){

            //跳转到用户界面
            System.out.println("==============================================================================");
            System.out.println("/**\n" +
                    " *                 .-~~~~~~~~~-._       _.-~~~~~~~~~-.\n" +
                    " *             __.'              ~.   .~              `.__\n" +
                    " *           .'//   悦     文      \\./     小      说    \\\\`.\n" +
                    " *         .'//                     |                     \\\\`.\n" +
                    " *       .'// .-~\"\"\"\"\"\"\"~~~~-._     |     _,-~~~~\"\"\"\"\"\"\"~-. \\\\`.\n" +
                    " *     .'//.-\"                 `-.  |  .-'                 \"-.\\\\`.\n" +
                    " *   .'//______.============-..   \\ | /   ..-============.______\\\\`.\n" +
                    " * .'______________________________\\|/______________________________`.\n" +
                    " *\n" +
                    " */\n");
            System.out.println("选项:");
            System.out.println("1.书城\t 2.个人中心\t 3.广场\t 4.书架\t 5.查看点赞的作品\t6.点赞");
            System.out.println("==================================================================================");
            Scanner input = new Scanner(System.in);
            int flag = input.nextInt();
            switch(flag) {
                //书城
                case 1:
                    System.out.println("6.搜索：");
                    System.out.println("1.推荐：");
                    System.out.println("2.分类：");
                    System.out.println("3.排行：");
                    System.out.println("4.男频：");
                    System.out.println("5.女频：");

                    Scanner input1 = new Scanner(System.in);
                    int flag1 = input1.nextInt();
                    switch (flag1) {
                        case 6:
                            System.out.println("请输入您想要看的书名/id/作者进行搜索：");
                            String sousuo = input.next();
                            for (Work sousuo1 : Dictionaries.worksList)
                            {
                                if (sousuo1.getId().equals(sousuo) || sousuo1.getName().equals(sousuo) || sousuo1.getUserName().equals(sousuo))
                                {
                                    System.out.println("为您匹配：");
                                    System.out.println("id\t"+sousuo1.getId()+"书名\t"+sousuo1.getName()+"作者\t"+sousuo1.getUserName());
                                }

                            }

                            System.out.println("1.查看\t2.退出");
                            Scanner sus1 = new Scanner(System.in);
                            int su2 = sus1.nextInt();
                            switch (su2)
                            {
                                case 1:
                                    System.out.print("请输入要查看的作品的id或书名");
                                    String workIdfl = input.next();
                                    for (Work oneWorkfl : Dictionaries.worksList)
                                    {
                                        if (oneWorkfl.getId().equals(workIdfl) || oneWorkfl.getName().equals(workIdfl))
                                        {
                                            //将点赞的作品存储到当前登录用户的集合中
                                            loginUser.getDuguo().add(oneWorkfl);
                                            for (int isj = 0; isj < Dictionaries.regUser.size(); isj++)
                                            {
                                                if (Dictionaries.regUser.get(isj).getUserName().equals(loginUser.getUserName()))
                                                {
                                                    Dictionaries.regUser.set(isj, loginUser);
                                                }
                                            }

                                            System.out.println("id:\t" + oneWorkfl.getId() + "书名：\t" + oneWorkfl.getName());
                                            System.out.println("作者：\t" + oneWorkfl.getUserName());
                                            System.out.println("详情：\n" + oneWorkfl.getContent());
                                            System.out.println("1.评论\t 2.阅读");

                                            Scanner inputyd = new Scanner(System.in);
                                            int flagyd = inputyd.nextInt();

                                            switch (flagyd)
                                            {
                                                case 1:
                                                    System.out.println("评论");
                                                    for (pinglun onepinglun : Dictionaries.pinglunlist)
                                                    {
                                                        if (onepinglun.getWork().equals(workIdfl) || onepinglun.getId().equals(workIdfl))
                                                        {
                                                            System.out.println(onepinglun.getUserName());
                                                            System.out.println(onepinglun.getStroy());
                                                        }
                                                    }

                                                    break;
                                                case 2:

                                                    for (Work gomawork : loginUser.getGomai())
                                                    {
                                                        if (gomawork.getName().equals(workIdfl) || gomawork.getId().equals(workIdfl))
                                                        {
                                                            String xiaoshuopath = "D:\\xiaoshuo\\" + oneWorkfl.getName() + ".txt";
                                                            readTxtFile(xiaoshuopath);
                                                            return;
                                                        }
                                                        else
                                                        {
                                                            System.out.println("您未购买本书，是否购买？(1.是\t2.否)");
                                                        }

                                                    }

                                                    Scanner inputgo = new Scanner(System.in);
                                                    int flaggo = inputgo.nextInt();

                                                    switch (flaggo)
                                                    {
                                                        case 1:

                                                            for (Work gom : Dictionaries.worksList)
                                                            {
                                                                if (gom.getId().equals(workIdfl) || gom.getName().equals(workIdfl))
                                                                {
                                                                    //将购买的作品存储到当前登录用户的集合中
                                                                    loginUser.getGomai().add(gom);
                                                                    for (int igm = 0; igm < Dictionaries.regUser.size(); igm++)
                                                                    {
                                                                        if (Dictionaries.regUser.get(igm).getUserName().equals(loginUser.getUserName()))
                                                                        {
                                                                            Dictionaries.regUser.set(igm, loginUser);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);
                                                            break;
                                                    }
                                            }
                                        }
                                    }

                                    break;
                                case 2:
                                    break;
                            }


                            break;
                        case 1:
                            int[] a = new int[40];
                            int i = 0;
                            for (Work oneWork : Dictionaries.worksList) {
                                a[i] = oneWork.getCount();
                                i++;
                            }

                            System.out.println("为您推荐书单：");
                            paixu(a);
                            System.out.println("1.购买\t2.查看\t3.退出");

                            Scanner keyid = new Scanner(System.in);
                            int flagki = keyid.nextInt();

                            switch (flagki)
                            {

                                case 1:
                                    System.out.print("请输入要购买的作品的id或书名：");
                                    String workId1 = input.next();


                                    for (Work gm : loginUser.getGomai())
                                    {
                                        if (gm.getId() == workId1 || gm.getName() == workId1)
                                        {
                                            System.out.println("您已购买过该书了");
                                        }
                                    }
                                    for (Work gm2 : Dictionaries.worksList)
                                    {
                                        if (gm2.getId().equals(workId1) || gm2.getName().equals(workId1))
                                        {
                                            //将购买的作品存储到当前登录用户的集合中
                                            loginUser.getGomai().add(gm2);
                                            for (int ig = 0; ig < Dictionaries.regUser.size(); ig++)
                                            {
                                                if (Dictionaries.regUser.get(ig).getUserName().equals(loginUser.getUserName()))
                                                {
                                                    Dictionaries.regUser.set(ig, loginUser);
                                                }
                                            }
                                        }
                                    }


                                    break;

                                case 2:
                                    System.out.print("请输入要查看的作品的id或书名");
                                    String workId = input.next();
                                    for (Work oneWork : Dictionaries.worksList)
                                    {
                                        if (oneWork.getId().equals(workId) || oneWork.getName().equals(workId))
                                        {
                                            //将点赞的作品存储到当前登录用户的集合中
                                            loginUser.getDuguo().add(oneWork);
                                            for (int i3 = 0; i < Dictionaries.regUser.size(); i3++)
                                            {
                                                if (Dictionaries.regUser.get(i3).getUserName().equals(loginUser.getUserName()))
                                                {
                                                    Dictionaries.regUser.set(i3, loginUser);
                                                }
                                            }

                                            System.out.println("id:\t" + oneWork.getId() + "书名：\t" + oneWork.getName());
                                            System.out.println("作者：\t" + oneWork.getUserName());
                                            System.out.println("详情：\n" + oneWork.getContent());
                                            System.out.println("1.评论\t 2.阅读");

                                            Scanner inputyd = new Scanner(System.in);
                                            int flagyd = inputyd.nextInt();

                                            switch (flagyd)
                                            {
                                                case 1:
                                                    System.out.println("评论");
                                                    for (pinglun onepinglun : Dictionaries.pinglunlist)
                                                    {
                                                        if (onepinglun.getWork().equals(workId) || onepinglun.getId().equals(workId))
                                                        {
                                                            System.out.println(onepinglun.getUserName());
                                                            System.out.println(onepinglun.getStroy());
                                                        }
                                                    }
                                                    break;
                                                case 2:

                                                    for (Work gomawork : loginUser.getGomai())
                                                    {
                                                        if (gomawork.getName().equals(workId) || gomawork.getId().equals(workId))
                                                        {
                                                            String xiaoshuopath = "D:\\xiaoshuo\\" + oneWork.getName() + ".txt";
                                                            readTxtFile(xiaoshuopath);
                                                            return;
                                                        }
                                                        else
                                                        {
                                                            System.out.println("您未购买本书，是否购买？(1.是\t2.否)");
                                                        }

                                                    }

                                                    Scanner inputgo = new Scanner(System.in);
                                                    int flaggo = inputgo.nextInt();

                                                    switch (flaggo)
                                                    {
                                                        case 1:

                                                            for (Work gom : Dictionaries.worksList)
                                                            {
                                                                if (gom.getId().equals(workId) || gom.getName().equals(workId))
                                                                {
                                                                    //将购买的作品存储到当前登录用户的集合中
                                                                    loginUser.getGomai().add(gom);
                                                                    for (int igm = 0; igm < Dictionaries.regUser.size(); igm++)
                                                                    {
                                                                        if (Dictionaries.regUser.get(igm).getUserName().equals(loginUser.getUserName()))
                                                                        {
                                                                            Dictionaries.regUser.set(igm, loginUser);
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                            PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);
                                                            break;

                                                        case 2:
                                                            break;
                                                    }
                                                    break;
                                                case 3:
                                                    break;

                                            }
                                        }
                                    }
                                    PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);

                                    break;
                            }

                            break;


/*
                            System.out.print("请输入要查看的作品的id或书名");
                            String workId = input.next();
                            for(Work oneWork:Dictionaries.worksList) {
                                if(oneWork.getId().equals(workId) || oneWork.getName().equals(workId))
                                {
                                    //将点赞的作品存储到当前登录用户的集合中
                                    loginUser.getDuguo().add(oneWork);
                                    for(int i3=0;i<Dictionaries.regUser.size();i3++)
                                    {
                                        if(Dictionaries.regUser.get(i3).getUserName().equals(loginUser.getUserName()))
                                        {
                                            Dictionaries.regUser.set(i3,loginUser);
                                        }
                                    }

                                    System.out.println("id:\t"+oneWork.getId()+"书名：\t"+oneWork.getName());
                                    System.out.println("作者：\t"+oneWork.getUserName());
                                    System.out.println("详情：\n"+oneWork.getContent());
                                    System.out.println("1.评论\t 2.阅读");

                                    Scanner inputyd = new Scanner(System.in);
                                    int flagyd = inputyd.nextInt();

                                    switch (flagyd)
                                    {
                                        case 1:
                                            System.out.println("评论");
                                            for (pinglun onepinglun:Dictionaries.pinglunlist){
                                                if(onepinglun.getWork().equals(workId) || onepinglun.getId().equals(workId))
                                                {
                                                    System.out.println(onepinglun.getUserName());
                                                    System.out.println(onepinglun.getStroy());
                                                }
                                            }

                                            break;
                                        case 2:

                                            for(Work gomawork:loginUser.getGomai())
                                            {
                                                if(gomawork.getName().equals(workId)||gomawork.getId().equals(workId))
                                                {
                                                    String xiaoshuopath = "D:\\xiaoshuo\\"+oneWork.getName()+".txt";
                                                    readTxtFile(xiaoshuopath);
                                                    break;

                                                }
                                                else
                                                {
                                                    System.out.println("您未购买本书，是否购买？(1.是\t2.否)");
                                                }

                                            }

                                            Scanner inputgo = new Scanner(System.in);
                                            int flaggo = inputgo.nextInt();

                                            switch (flaggo)
                                            {
                                                case 1:

                                                    for(Work gom:Dictionaries.worksList) {
                                                        if(gom.getId().equals(workId) || gom.getName().equals(workId))
                                                        {
                                                            //将购买的作品存储到当前登录用户的集合中
                                                            loginUser.getGomai().add(gom);
                                                            for(int igm=0;igm<Dictionaries.regUser.size();igm++)
                                                            {
                                                                if(Dictionaries.regUser.get(igm).getUserName().equals(loginUser.getUserName()))
                                                                {
                                                                    Dictionaries.regUser.set(igm,loginUser);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data",Dictionaries.regUser);
                                                    break;

                                                case 2:
                                                    break;
                                            }



                                            break;
                                    }
                                }
                            }
                            PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data",Dictionaries.regUser);

                            break;

 */












/*
                            System.out.print("请输入要购买的作品的id或书名：");
                            String workId1 = input.next();
                            for(Work oneWork:Dictionaries.worksList) {
                                if(oneWork.getId().equals(workId1) || oneWork.getName().equals(workId))
                                {
                                    //将购买的作品存储到当前登录用户的集合中
                                    loginUser.getGomai().add(oneWork);
                                    for(int i2=0;i2<Dictionaries.regUser.size();i2++)
                                    {
                                        if(Dictionaries.regUser.get(i2).getUserName().equals(loginUser.getUserName()))
                                        {
                                            Dictionaries.regUser.set(i2,loginUser);
                                        }
                                    }
                                }
                            }
                            PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data",Dictionaries.regUser);


                            break;

 */


                        //分类
                        case 2:
                            System.out.println("1.玄幻");
                            for (Work oneWork : Dictionaries.worksList) {
                                if (oneWork.getType() == 1) {
                                    System.out.println("id:" + oneWork.getId() + '\t' + "书名：" + oneWork.getName());
                                }

                            }
                            System.out.println("2.武侠");
                            for (Work oneWork : Dictionaries.worksList) {
                                if (oneWork.getType() == 2) {
                                    System.out.println("id:" + oneWork.getId() + '\t' + "书名：" + oneWork.getName());
                                }

                            }
                            System.out.println("3.言情");
                            for (Work oneWork : Dictionaries.worksList) {
                                if (oneWork.getType() == 3) {
                                    System.out.println("id:" + oneWork.getId() + '\t' + "书名：" + oneWork.getName());
                                }

                            }
                            System.out.println("4.短篇合集");
                            for (Work oneWork : Dictionaries.worksList) {
                                if (oneWork.getType() == 4) {
                                    System.out.println("id:" + oneWork.getId() + '\t' + "书名：" + oneWork.getName());
                                }

                            }

                            System.out.print("请输入要查看的作品的id或书名");
                            String workIdfl = input.next();
                            for (Work oneWorkfl : Dictionaries.worksList)
                            {
                                if (oneWorkfl.getId().equals(workIdfl) || oneWorkfl.getName().equals(workIdfl))
                                {
                                    //将点赞的作品存储到当前登录用户的集合中
                                    loginUser.getDuguo().add(oneWorkfl);
                                    for (int isj = 0; isj < Dictionaries.regUser.size(); isj++)
                                    {
                                        if (Dictionaries.regUser.get(isj).getUserName().equals(loginUser.getUserName()))
                                        {
                                            Dictionaries.regUser.set(isj, loginUser);
                                        }
                                    }

                                    System.out.println("id:\t" + oneWorkfl.getId() + "书名：\t" + oneWorkfl.getName());
                                    System.out.println("作者：\t" + oneWorkfl.getUserName());
                                    System.out.println("详情：\n" + oneWorkfl.getContent());
                                    System.out.println("1.评论\t 2.阅读");

                                    Scanner inputyd = new Scanner(System.in);
                                    int flagyd = inputyd.nextInt();

                                    switch (flagyd)
                                    {
                                        case 1:
                                            System.out.println("评论");
                                            for (pinglun onepinglun : Dictionaries.pinglunlist)
                                            {
                                                if (onepinglun.getWork().equals(workIdfl) || onepinglun.getId().equals(workIdfl))
                                                {
                                                    System.out.println(onepinglun.getUserName());
                                                    System.out.println(onepinglun.getStroy());
                                                }
                                            }

                                            break;
                                        case 2:

                                            for (Work gomawork : loginUser.getGomai())
                                            {
                                                if (gomawork.getName().equals(workIdfl) || gomawork.getId().equals(workIdfl))
                                                {
                                                    String xiaoshuopath = "D:\\xiaoshuo\\" + oneWorkfl.getName() + ".txt";
                                                    readTxtFile(xiaoshuopath);
                                                    return;
                                                }
                                                else
                                                {
                                                    System.out.println("您未购买本书，是否购买？(1.是\t2.否)");
                                                }

                                            }

                                            Scanner inputgo = new Scanner(System.in);
                                            int flaggo = inputgo.nextInt();

                                            switch (flaggo)
                                            {
                                                case 1:

                                                    for (Work gom : Dictionaries.worksList)
                                                    {
                                                        if (gom.getId().equals(workIdfl) || gom.getName().equals(workIdfl))
                                                        {
                                                            //将购买的作品存储到当前登录用户的集合中
                                                            loginUser.getGomai().add(gom);
                                                            for (int igm = 0; igm < Dictionaries.regUser.size(); igm++)
                                                            {
                                                                if (Dictionaries.regUser.get(igm).getUserName().equals(loginUser.getUserName()))
                                                                {
                                                                    Dictionaries.regUser.set(igm, loginUser);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);
                                                    break;
                                            }
                                    }
                                }
                            }


                            break;

                            //排行
                        case 3:
                            int[] a1 = new int[40];
                            int i1 = 0;
                            for (Work oneWork : Dictionaries.worksList) {
                                a1[i1] = oneWork.getCount();
                                i1++;
                            }
                            System.out.println("综合榜：");
                            paixu1(a1);
                            System.out.println("玄幻榜：");
                            pahang1(a1);
                            System.out.println("武侠榜：");
                            pahang2(a1);
                            System.out.println("言情榜：");
                            pahang3(a1);
                            System.out.println("短篇合集榜：");
                            pahang4(a1);

                            System.out.print("请输入要查看的作品的id或书名");
                            String workIdph = input.next();
                            for (Work oneWorkph : Dictionaries.worksList)
                            {
                                if (oneWorkph.getId().equals(workIdph) || oneWorkph.getName().equals(workIdph))
                                {
                                    //将点赞的作品存储到当前登录用户的集合中
                                    loginUser.getDuguo().add(oneWorkph);
                                    for (int isj = 0; isj < Dictionaries.regUser.size(); isj++)
                                    {
                                        if (Dictionaries.regUser.get(isj).getUserName().equals(loginUser.getUserName()))
                                        {
                                            Dictionaries.regUser.set(isj, loginUser);
                                        }
                                    }

                                    System.out.println("id:\t" + oneWorkph.getId() + "书名：\t" + oneWorkph.getName());
                                    System.out.println("作者：\t" + oneWorkph.getUserName());
                                    System.out.println("详情：\n" + oneWorkph.getContent());
                                    System.out.println("1.评论\t 2.阅读");

                                    Scanner inputyd = new Scanner(System.in);
                                    int flagyd = inputyd.nextInt();

                                    switch (flagyd)
                                    {
                                        case 1:
                                            System.out.println("评论");
                                            for (pinglun onepinglun : Dictionaries.pinglunlist)
                                            {
                                                if (onepinglun.getWork().equals(workIdph) || onepinglun.getId().equals(workIdph))
                                                {
                                                    System.out.println(onepinglun.getUserName());
                                                    System.out.println(onepinglun.getStroy());
                                                }
                                            }

                                            break;
                                        case 2:

                                            for (Work gomawork : loginUser.getGomai())
                                            {
                                                if (gomawork.getName().equals(workIdph) || gomawork.getId().equals(workIdph))
                                                {
                                                    String xiaoshuopath = "D:\\xiaoshuo\\" + oneWorkph.getName() + ".txt";
                                                    readTxtFile(xiaoshuopath);
                                                    return;
                                                }
                                                else
                                                {
                                                    System.out.println("您未购买本书，是否购买？(1.是\t2.否)");
                                                }

                                            }

                                            Scanner inputgo = new Scanner(System.in);
                                            int flaggo = inputgo.nextInt();

                                            switch (flaggo)
                                            {
                                                case 1:

                                                    for (Work gom : Dictionaries.worksList)
                                                    {
                                                        if (gom.getId().equals(workIdph) || gom.getName().equals(workIdph))
                                                        {
                                                            //将购买的作品存储到当前登录用户的集合中
                                                            loginUser.getGomai().add(gom);
                                                            for (int igm = 0; igm < Dictionaries.regUser.size(); igm++)
                                                            {
                                                                if (Dictionaries.regUser.get(igm).getUserName().equals(loginUser.getUserName()))
                                                                {
                                                                    Dictionaries.regUser.set(igm, loginUser);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);
                                                    break;
                                            }
                                    }
                                }
                            }

                            break;

                        case 4:
                            System.out.println("欢迎来到男频！");
                            for (Work oneWork : Dictionaries.worksList) {
                                if (oneWork.getType() == 1 || oneWork.getType() == 2) {
                                    System.out.println("id:" + oneWork.getId() + '\t' + "书名：" + oneWork.getName());
                                }

                            }

                            System.out.print("请输入要查看的作品的id或书名");
                            String workIdnp = input.next();
                            for (Work oneWorknp : Dictionaries.worksList)
                            {
                                if (oneWorknp.getId().equals(workIdnp) || oneWorknp.getName().equals(workIdnp))
                                {
                                    //将点赞的作品存储到当前登录用户的集合中
                                    loginUser.getDuguo().add(oneWorknp);
                                    for (int isj = 0; isj < Dictionaries.regUser.size(); isj++)
                                    {
                                        if (Dictionaries.regUser.get(isj).getUserName().equals(loginUser.getUserName()))
                                        {
                                            Dictionaries.regUser.set(isj, loginUser);
                                        }
                                    }

                                    System.out.println("id:\t" + oneWorknp.getId() + "书名：\t" + oneWorknp.getName());
                                    System.out.println("作者：\t" + oneWorknp.getUserName());
                                    System.out.println("详情：\n" + oneWorknp.getContent());
                                    System.out.println("1.评论\t 2.阅读");

                                    Scanner inputyd = new Scanner(System.in);
                                    int flagyd = inputyd.nextInt();

                                    switch (flagyd)
                                    {
                                        case 1:
                                            System.out.println("评论");
                                            for (pinglun onepinglun : Dictionaries.pinglunlist)
                                            {
                                                if (onepinglun.getWork().equals(workIdnp) || onepinglun.getId().equals(workIdnp))
                                                {
                                                    System.out.println(onepinglun.getUserName());
                                                    System.out.println(onepinglun.getStroy());
                                                }
                                            }

                                            break;
                                        case 2:

                                            for (Work gomawork : loginUser.getGomai())
                                            {
                                                if (gomawork.getName().equals(workIdnp) || gomawork.getId().equals(workIdnp))
                                                {
                                                    String xiaoshuopath = "D:\\xiaoshuo\\" + oneWorknp.getName() + ".txt";
                                                    readTxtFile(xiaoshuopath);
                                                    return;
                                                }
                                                else
                                                {
                                                    System.out.println("您未购买本书，是否购买？(1.是\t2.否)");
                                                }

                                            }

                                            Scanner inputgo = new Scanner(System.in);
                                            int flaggo = inputgo.nextInt();

                                            switch (flaggo)
                                            {
                                                case 1:

                                                    for (Work gom : Dictionaries.worksList)
                                                    {
                                                        if (gom.getId().equals(workIdnp) || gom.getName().equals(workIdnp))
                                                        {
                                                            //将购买的作品存储到当前登录用户的集合中
                                                            loginUser.getGomai().add(gom);
                                                            for (int igm = 0; igm < Dictionaries.regUser.size(); igm++)
                                                            {
                                                                if (Dictionaries.regUser.get(igm).getUserName().equals(loginUser.getUserName()))
                                                                {
                                                                    Dictionaries.regUser.set(igm, loginUser);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);
                                                    break;
                                            }
                                    }
                                }
                            }

                            break;
                        case 5:
                            System.out.println("欢迎来到女频！");
                            for (Work oneWork : Dictionaries.worksList)
                            {
                                if (oneWork.getType() == 3 || oneWork.getType() == 4)
                                {
                                    System.out.println("id:" + oneWork.getId() + '\t' + "书名：" + oneWork.getName());
                                }

                            }

                            System.out.print("请输入要查看的作品的id或书名");
                            String workIdvp = input.next();
                            for (Work oneWorkvp : Dictionaries.worksList)
                            {
                                if (oneWorkvp.getId().equals(workIdvp) || oneWorkvp.getName().equals(workIdvp))
                                {
                                    //将点赞的作品存储到当前登录用户的集合中
                                    loginUser.getDuguo().add(oneWorkvp);
                                    for (int isj = 0; isj < Dictionaries.regUser.size(); isj++)
                                    {
                                        if (Dictionaries.regUser.get(isj).getUserName().equals(loginUser.getUserName()))
                                        {
                                            Dictionaries.regUser.set(isj, loginUser);
                                        }
                                    }

                                    System.out.println("id:\t" + oneWorkvp.getId() + "书名：\t" + oneWorkvp.getName());
                                    System.out.println("作者：\t" + oneWorkvp.getUserName());
                                    System.out.println("详情：\n" + oneWorkvp.getContent());
                                    System.out.println("1.评论\t 2.阅读");

                                    Scanner inputyd = new Scanner(System.in);
                                    int flagyd = inputyd.nextInt();

                                    switch (flagyd)
                                    {
                                        case 1:
                                            System.out.println("评论");
                                            for (pinglun onepinglun : Dictionaries.pinglunlist)
                                            {
                                                if (onepinglun.getWork().equals(workIdvp) || onepinglun.getId().equals(workIdvp))
                                                {
                                                    System.out.println(onepinglun.getUserName());
                                                    System.out.println(onepinglun.getStroy());
                                                }
                                            }

                                            break;
                                        case 2:

                                            for (Work gomawork : loginUser.getGomai())
                                            {
                                                if (gomawork.getName().equals(workIdvp) || gomawork.getId().equals(workIdvp))
                                                {
                                                    String xiaoshuopath = "D:\\xiaoshuo\\" + oneWorkvp.getName() + ".txt";
                                                    readTxtFile(xiaoshuopath);
                                                    return;
                                                }
                                                else
                                                {
                                                    System.out.println("您未购买本书，是否购买？(1.是\t2.否)");
                                                }

                                            }

                                            Scanner inputgo = new Scanner(System.in);
                                            int flaggo = inputgo.nextInt();

                                            switch (flaggo)
                                            {
                                                case 1:

                                                    for (Work gom : Dictionaries.worksList)
                                                    {
                                                        if (gom.getId().equals(workIdvp) || gom.getName().equals(workIdvp))
                                                        {
                                                            //将购买的作品存储到当前登录用户的集合中
                                                            loginUser.getGomai().add(gom);
                                                            for (int igm = 0; igm < Dictionaries.regUser.size(); igm++)
                                                            {
                                                                if (Dictionaries.regUser.get(igm).getUserName().equals(loginUser.getUserName()))
                                                                {
                                                                    Dictionaries.regUser.set(igm, loginUser);
                                                                }
                                                            }
                                                        }
                                                    }
                                                    PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);
                                                    break;
                                            }
                                    }
                                }
                            }
                            break;


                    }
                    break;


                //个人中心
                case 2:
                    System.out.println("1.我的书籍\t" + "2.书单\t" + "3.笔记\t" + "4.我的评论\t" + "5.余额");
                    Scanner input2 = new Scanner(System.in);
                    int flag2 = input2.nextInt();
                    switch (flag2) {
                        case 1:
                            System.out.println("已购买书籍：");
                            for (Work oneWork : loginUser.getGomai()) {
                                System.out.println("id:\t" + oneWork.getId() + "书名：\t" + oneWork.getName());
                            }
                            break;
                        case 2:
                            System.out.println("我的书单（曾读过）：");
                            for (Work oneWork : loginUser.getDuguo()) {
                                System.out.println("id:\t" + oneWork.getId() + "\t" + "书名：\t" + oneWork.getName());
                            }
                            break;
                        case 3:
                            System.out.println("我的笔记：");
                            break;
                        case 4:
                            System.out.println("我的评论：");
                            for (pinglun onepinglun : Dictionaries.pinglunlist) {
                                if (onepinglun.getUserName().equals(loginUser.getUserName())) {
                                    System.out.println(onepinglun.getWork() + "\n" + onepinglun.getStroy());
                                }
                            }


                            break;
                        case 5:
                            System.out.println("我的余额：");
                            System.out.println(loginUser.getMoneny());
                            System.out.println("充值(请按1)\t 返回(请按2)");
                            Scanner inputcz = new Scanner(System.in);
                            int flagcz = inputcz.nextInt();


                            switch (flagcz) {
                                case 1:
                                    System.out.println("请输入充值金额：");
                                    Scanner inputje = new Scanner(System.in);
                                    int flagje = inputje.nextInt();
                                    int flagje2 = loginUser.getMoneny() + flagje;
                                    loginUser.setMoneny(flagje2);
                                    for (int icz = 0; icz < Dictionaries.regUser.size(); icz++) {
                                        if (Dictionaries.regUser.get(icz).getUserName().equals(loginUser.getUserName())) {
                                            Dictionaries.regUser.set(icz, loginUser);
                                        }
                                    }
                                    PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);
                                    System.out.println("充值成功！当前账户余额：\t" + flagje2);
                                    break;
                                case 2:
                                    break;


                            }

                            break;
                    }
                    break;


                //广场
                case 3:
                    System.out.println("欢迎来到广场！");
                    System.out.println("1.关注\t 2.热门");
                    Scanner guac = new Scanner(System.in);
                    int fguac = guac.nextInt();
                    switch (fguac)
                    {
                        case 1:
                            System.out.println("我的关注:");
                            for (Work guanzhu : loginUser.getGomai())
                            {
                                System.out.println("作者："+guanzhu.getUserName());
                                System.out.println(guanzhu.getPinglun());
                            }
                            break;
                        case 2:
                            System.out.println("热门:");
                            int[] arm = new int[50];
                            int irm = 0;
                            for (Work oneWorkrm : Dictionaries.worksList) {
                                arm[irm] = oneWorkrm.getCount();
                                irm++;
                            }

                            remen(arm);


                            for (Work guanzhu : loginUser.getGomai()) {
                                System.out.println("作者："+guanzhu.getUserName());
                                System.out.println(guanzhu.getPinglun());
                            }


                            break;
                    }


                    break;


                //书架
                case 4:
                    System.out.println("这是你的书架");
                    for (Work oneWork : loginUser.getGomai()) {
                        System.out.println("id:\t" + oneWork.getId() + "书名：\t" + oneWork.getName());

                    }

                    System.out.print("请输入要查看的作品的id或书名");
                    String workIdsj = input.next();
                    for (Work oneWorksj : Dictionaries.worksList)
                    {
                        if (oneWorksj.getId().equals(workIdsj) || oneWorksj.getName().equals(workIdsj))
                        {
                            //将点赞的作品存储到当前登录用户的集合中
                            loginUser.getDuguo().add(oneWorksj);
                            for (int isj = 0; isj < Dictionaries.regUser.size(); isj++)
                            {
                                if (Dictionaries.regUser.get(isj).getUserName().equals(loginUser.getUserName()))
                                {
                                    Dictionaries.regUser.set(isj, loginUser);
                                }
                            }

                            System.out.println("id:\t" + oneWorksj.getId() + "书名：\t" + oneWorksj.getName());
                            System.out.println("作者：\t" + oneWorksj.getUserName());
                            System.out.println("详情：\n" + oneWorksj.getContent());
                            System.out.println("1.评论\t 2.阅读");

                            Scanner inputyd = new Scanner(System.in);
                            int flagyd = inputyd.nextInt();

                            switch (flagyd)
                            {
                                case 1:
                                    System.out.println("评论");
                                    for (pinglun onepinglun : Dictionaries.pinglunlist)
                                    {
                                        if (onepinglun.getWork().equals(workIdsj) || onepinglun.getId().equals(workIdsj))
                                        {
                                            System.out.println(onepinglun.getUserName());
                                            System.out.println(onepinglun.getStroy());
                                        }
                                    }

                                    break;
                                case 2:

                                    for (Work gomawork : loginUser.getGomai())
                                    {
                                        if (gomawork.getName().equals(workIdsj) || gomawork.getId().equals(workIdsj))
                                        {
                                            String xiaoshuopath = "D:\\xiaoshuo\\" + oneWorksj.getName() + ".txt";
                                            readTxtFile(xiaoshuopath);
                                            return;
                                        }
                                        else
                                        {
                                            System.out.println("您未购买本书，是否购买？(1.是\t2.否)");
                                        }

                                    }

                                    Scanner inputgo = new Scanner(System.in);
                                    int flaggo = inputgo.nextInt();

                                    switch (flaggo)
                                    {
                                        case 1:

                                            for (Work gom : Dictionaries.worksList)
                                            {
                                                if (gom.getId().equals(workIdsj) || gom.getName().equals(workIdsj))
                                                {
                                                    //将购买的作品存储到当前登录用户的集合中
                                                    loginUser.getGomai().add(gom);
                                                    for (int igm = 0; igm < Dictionaries.regUser.size(); igm++)
                                                    {
                                                        if (Dictionaries.regUser.get(igm).getUserName().equals(loginUser.getUserName()))
                                                        {
                                                            Dictionaries.regUser.set(igm, loginUser);
                                                        }
                                                    }
                                                }
                                            }
                                            PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data", Dictionaries.regUser);
                                            break;
                                    }
                            }
                        }
                    }






                case 6:

                    System.out.print("请输入要点赞的作品的id：");
                    String workId = input.next();
                    for(Work oneWork:Dictionaries.worksList) {
                        if(oneWork.getId().equals(workId)){
                            //将点赞的作品存储到当前登录用户的集合中
                            loginUser.getLikeWorkList().add(oneWork);
                            for(int i=0;i<Dictionaries.regUser.size();i++){
                                if(Dictionaries.regUser.get(i).getUserName().equals(loginUser.getUserName())) {
                                    Dictionaries.regUser.set(i,loginUser);
                                }
                            }
                        }
                    }
                    PersistenceUtils.dataPersistence("D:\\javadatatext\\user.data",Dictionaries.regUser);
                    break;

               /* case 8://上传作品
                    System.out.print("请输入ID：");
                    String id = input.next();
                    System.out.print("请输入标题：");
                    String title = input.next();
                    System.out.print("请输入详情：");
                    String content = input.next();
                    System.out.print("请输入类型（1娱乐,2生活,3社会,4国际）：");
                    int type = input.nextInt();
                    Work newWork = new Work(id,title,content,0,type,loginUser.getUserName());
                    Dictionaries.worksList.add(newWork);
                    System.out.println("恭喜，上传成功！");
                    break;

                */
                case 5:
                    for(Work oneWork:loginUser.getLikeWorkList()){
                        System.out.println("id:"+oneWork.getId()+'\t'+"书名："+oneWork.getName());
                    }
            }
        }
        }


}
