package gongju;

import classes.Work;

public class paixu {
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

       for (int m:a)
       {
           System.out.println(m);
       }
       int j = 0;
       for(Work oneWork:Dictionaries.worksList)
       {
           if(oneWork.getCount()==a[j])
           {
               System.out.println("id:"+oneWork.getId()+"\t"+"书名："+oneWork.getName());
           }
           j++;
       }

   }
}
