package ClientDemo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * JDialogVoiceTalk.java
 *
 * Created on 2009-11-30, 20:42:14
 */

/**
 *
 * @author Administrator
 */

import ClientDemo.HCNetSDK.FVoiceDataCallBack_V30;
import ClientDemo.HCNetSDK.NET_DVR_DEVICEINFO_V30;
import ClientDemo.HCNetSDK.NET_DVR_WORKSTATE_V30;

import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*****************************************************************************
 *类 ：    JDialogVoiceTalk
 *类描述 ：语音对讲
 ****************************************************************************/
public class JDialogVoiceTalk extends javax.swing.JDialog {

    static HCNetSDK hCNetSDK = HCNetSDK.INSTANCE;
    NativeLong m_lUserID;//用户ID
    HCNetSDK.NET_DVR_DEVICEINFO_V30 m_strDeviceInfo;//设备信息
    HCNetSDK.NET_DVR_WORKSTATE_V30 m_strWorkState;//工作状态
    FVoiceDataCallBack fVoiceDataCallBack;//回调函数
    int m_iSel;//音频通道
    boolean m_bInitialed;//对话框是否已初始化

    FileWriter fLocal ;//本地音频文件
    FileWriter fDevice ;//设备发送的音频文件

    /*************************************************
    函数:      JDialogVoiceTalk
    函数描述:  构造函数   Creates new form JDialogVoiceTalk
     *************************************************/
    public JDialogVoiceTalk(java.awt.Frame parent, boolean modal, NativeLong lUserID, HCNetSDK.NET_DVR_DEVICEINFO_V30 strDeviceInfo) 
    {
        super(parent, modal);
        try
        {
            initComponents();
            m_lUserID = lUserID;
            m_strDeviceInfo = strDeviceInfo;
            m_iSel = 0;
            fVoiceDataCallBack = new FVoiceDataCallBack();
            fLocal = new FileWriter("D:\\local.264");
            fDevice = new FileWriter("D:\\device.264");
           
            //初始化对话框
            initialDialog();
            m_bInitialed = true;
        } catch (IOException ex)
        {
            Logger.getLogger(JDialogVoiceTalk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxVoiceChannel = new javax.swing.JComboBox();
        jLabelStatus = new javax.swing.JLabel();
        jButtonStart = new javax.swing.JButton();
        jButtonStop = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxDataType = new javax.swing.JComboBox();
        jButtonRefresh = new javax.swing.JButton();
        jButtonExit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("语音对讲");

        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("对讲通道");

        jComboBoxVoiceChannel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxVoiceChannelActionPerformed(evt);
            }
        });

        jLabelStatus.setText("未占用");

        jButtonStart.setText("开始对讲");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });

        jButtonStop.setText("停止对讲");
        jButtonStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStopActionPerformed(evt);
            }
        });

        jLabel2.setText("回调数据类型");

        jComboBoxDataType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "非PCM", "PCM" }));

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jButtonStart)
                                .addGap(36, 36, 36)
                                .addComponent(jButtonStop))
                            .addGroup(jPanelLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxVoiceChannel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxDataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxVoiceChannel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatus))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonStart)
                    .addComponent(jButtonStop))
                .addGap(18, 18, 18)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxDataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonRefresh.setText("刷新");
        jButtonRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRefreshActionPerformed(evt);
            }
        });

        jButtonExit.setText("退出");
        jButtonExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jButtonRefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(jButtonExit)
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRefresh)
                    .addComponent(jButtonExit))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

     /*************************************************
     函数:      "开始对讲" 按钮单击响应函数
     函数描述:	开始对讲
      *************************************************/
    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt)                                             
    {                                                 
	//这里采用全局的句柄进行操作,因为子对话框销毁后语音对讲还可以进行,用全局的变量保存句柄
        ClientDemo.g_lVoiceHandle = hCNetSDK.NET_DVR_StartVoiceCom_V30(m_lUserID, m_iSel+1, jComboBoxDataType.getSelectedIndex() == 1, fVoiceDataCallBack,  null);
	if ( ClientDemo.g_lVoiceHandle.intValue() == -1)
	{
		JOptionPane.showMessageDialog(this, "语音对讲失败");
                return;
	}
	EnableCtrl();
    }                                            

    /*************************************************
     函数:      "停止对讲" 按钮单击响应函数
     函数描述:	停止对讲
      *************************************************/
    private void jButtonStopActionPerformed(java.awt.event.ActionEvent evt)                                            
    {                                                
       if ( ClientDemo.g_lVoiceHandle.intValue() >= 0)
	{
		if (hCNetSDK.NET_DVR_StopVoiceCom( ClientDemo.g_lVoiceHandle))
		{
			 ClientDemo.g_lVoiceHandle.setValue(-1);
		}
		else
		{
			JOptionPane.showMessageDialog(this, "关闭对讲失败");
		}
	}
	EnableCtrl();
    }                                           

    /*************************************************
     函数:      "对讲通道" 选项改变事件响应函数
     函数描述:  显示通道状态
      *************************************************/
    private void jComboBoxVoiceChannelActionPerformed(java.awt.event.ActionEvent evt)                                                      
    {                                                          
	if(!m_bInitialed)
        {
            return;
        }
        m_iSel = jComboBoxVoiceChannel.getSelectedIndex();
	if (0 == m_strWorkState.byAudioChanStatus[m_iSel])
	{
	       jLabelStatus.setText("未使用");
                jLabelStatus.setVisible(true);
	}
	else if (1 == m_strWorkState.byAudioChanStatus[m_iSel])
	{
	            jLabelStatus.setText("已使用");
                    jLabelStatus.setVisible(true);
	}
	else if (0xff == m_strWorkState.byAudioChanStatus[m_iSel])
	{
		   jLabelStatus.setVisible(false);
	}
    }                                                     

    /*************************************************
     函数:      "刷新" 按钮单击响应函数
     函数描述:	刷新状态
      *************************************************/
    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt)                                               
    {                                                   
	    if (!hCNetSDK.NET_DVR_GetDVRWorkState_V30(m_lUserID, m_strWorkState))
        {
            JOptionPane.showMessageDialog(this, "获取工作状态失败");
            jLabelStatus.setVisible(false);
            return;
        }

	m_iSel = jComboBoxVoiceChannel.getSelectedIndex();
	if (0 == m_strWorkState.byAudioChanStatus[m_iSel])
	{
	       jLabelStatus.setText("未使用");
                jLabelStatus.setVisible(true);
	}
	else if (1 == m_strWorkState.byAudioChanStatus[m_iSel])
	{
	            jLabelStatus.setText("已使用");
                    jLabelStatus.setVisible(true);
	}
	else if (0xff == m_strWorkState.byAudioChanStatus[m_iSel])
	{
		   jLabelStatus.setVisible(false);
	}
    }                                              

    /*************************************************
     函数:      "退出" 按钮单击响应函数
     函数描述:   销毁对话框
      *************************************************/
    private void jButtonExitActionPerformed(java.awt.event.ActionEvent evt)                                            
    {                                                
       dispose();
    }                                           

    /*************************************************
     函数:      initialDialog
     函数描述:  初始化对话框
      *************************************************/
    private void initialDialog()
    {
        EnableCtrl();
        switch (m_strDeviceInfo.byAudioChanNum)
        {
            case 1:
                jComboBoxVoiceChannel.addItem("Audio1");
                break;
            case 2:
                jComboBoxVoiceChannel.addItem("Audio1");
                jComboBoxVoiceChannel.addItem("Audio2");
                break;
            default:
                break;
        }

        m_strWorkState = new HCNetSDK.NET_DVR_WORKSTATE_V30();
        if (!hCNetSDK.NET_DVR_GetDVRWorkState_V30(m_lUserID, m_strWorkState))
        {
            JOptionPane.showMessageDialog(this, "获取工作状态失败");
            jLabelStatus.setVisible(false);
        } else
        {

            if (0 == m_strWorkState.byAudioChanStatus[m_iSel])
            {
                jLabelStatus.setText("未使用");
                jLabelStatus.setVisible(true);
            } else
            {
                if (1 == m_strWorkState.byAudioChanStatus[m_iSel])
                {
                    jLabelStatus.setText("已使用");
                    jLabelStatus.setVisible(true);
                } else
                {
                    if (0xff == m_strWorkState.byAudioChanStatus[m_iSel])
                    {
                        jLabelStatus.setVisible(false);
                    }
                }
            }
        }
    }

    /*************************************************
     函数:      EnableCtrl
     函数描述:  控件Enable属性
      *************************************************/
    private void EnableCtrl()
    {
        boolean bVoiceTalk = false;
        if ( ClientDemo.g_lVoiceHandle.intValue() >= 0)
        {
            bVoiceTalk = true;
            jLabelStatus.setText("已使用");
        } else
        {
            bVoiceTalk = false;
            jLabelStatus.setText("未使用");
        }
        jButtonStart.setEnabled(!bVoiceTalk);
        jButtonStop.setEnabled(bVoiceTalk);
        jComboBoxVoiceChannel.setEnabled(!bVoiceTalk);
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButtonExit;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonStart;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JComboBox jComboBoxDataType;
    private javax.swing.JComboBox jComboBoxVoiceChannel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JPanel jPanel;
    // End of variables declaration                   

    /******************************************************************************
     *内部类:   FVoiceDataCallBack
     *          实现对讲音频数据回调
     ******************************************************************************/
    class FVoiceDataCallBack implements HCNetSDK.FVoiceDataCallBack_V30
    {
       //对对讲的音频数据进行回调操作,以下写入文件操作
        public void invoke(NativeLong lVoiceComHandle, String pRecvDataBuffer, int dwBufSize, byte byAudioFlag, Pointer pUser)
        {
            //byAudioFlag为0表示本地文件,为1表示设备的音频文件
            if (byAudioFlag == 0)
            {
                try
                {
                    fLocal.write(pRecvDataBuffer);
                    fLocal.flush();
                } catch (IOException ex)
                {
                    Logger.getLogger(JDialogVoiceTalk.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else
            {
                if (byAudioFlag == 1)
                {
                    try
                    {
                        fDevice.write(pRecvDataBuffer);
                        fDevice.flush();
                    } catch (IOException ex)
                    {
                        Logger.getLogger(JDialogVoiceTalk.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }


}
