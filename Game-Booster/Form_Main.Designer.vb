<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form_Main
    Inherits System.Windows.Forms.Form

    'Form overrides dispose to clean up the component list.
    <System.Diagnostics.DebuggerNonUserCode()> _
    Protected Overrides Sub Dispose(ByVal disposing As Boolean)
        Try
            If disposing AndAlso components IsNot Nothing Then
                components.Dispose()
            End If
        Finally
            MyBase.Dispose(disposing)
        End Try
    End Sub

    'Required by the Windows Form Designer
    Private components As System.ComponentModel.IContainer

    'NOTE: The following procedure is required by the Windows Form Designer
    'It can be modified using the Windows Form Designer.  
    'Do not modify it using the code editor.
    <System.Diagnostics.DebuggerStepThrough()> _
    Private Sub InitializeComponent()
        Me.components = New System.ComponentModel.Container()
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(Form_Main))
        Me.PictureBox_Logo = New System.Windows.Forms.PictureBox()
        Me.PictureBox_BoostOFF = New System.Windows.Forms.PictureBox()
        Me.PictureBox_BoostON = New System.Windows.Forms.PictureBox()
        Me.PictureBox_Online_ON = New System.Windows.Forms.PictureBox()
        Me.PictureBox_Online_OFF = New System.Windows.Forms.PictureBox()
        Me.NotifyIcon_Main = New System.Windows.Forms.NotifyIcon(Me.components)
        Me.ContextMenuStrip_Main = New System.Windows.Forms.ContextMenuStrip(Me.components)
        Me.AboutMeToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.BoostOnToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.BoostOfToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.CloseToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.Process1 = New System.Diagnostics.Process()
        Me.ServiceController1 = New System.ServiceProcess.ServiceController()
        CType(Me.PictureBox_Logo, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox_BoostOFF, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox_BoostON, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox_Online_ON, System.ComponentModel.ISupportInitialize).BeginInit()
        CType(Me.PictureBox_Online_OFF, System.ComponentModel.ISupportInitialize).BeginInit()
        Me.ContextMenuStrip_Main.SuspendLayout()
        Me.SuspendLayout()
        '
        'PictureBox_Logo
        '
        Me.PictureBox_Logo.BackColor = System.Drawing.Color.Transparent
        Me.PictureBox_Logo.Image = CType(resources.GetObject("PictureBox_Logo.Image"), System.Drawing.Image)
        Me.PictureBox_Logo.Location = New System.Drawing.Point(244, 131)
        Me.PictureBox_Logo.Name = "PictureBox_Logo"
        Me.PictureBox_Logo.Size = New System.Drawing.Size(200, 77)
        Me.PictureBox_Logo.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize
        Me.PictureBox_Logo.TabIndex = 0
        Me.PictureBox_Logo.TabStop = False
        '
        'PictureBox_BoostOFF
        '
        Me.PictureBox_BoostOFF.BackColor = System.Drawing.Color.Transparent
        Me.PictureBox_BoostOFF.Image = CType(resources.GetObject("PictureBox_BoostOFF.Image"), System.Drawing.Image)
        Me.PictureBox_BoostOFF.Location = New System.Drawing.Point(44, 109)
        Me.PictureBox_BoostOFF.Name = "PictureBox_BoostOFF"
        Me.PictureBox_BoostOFF.Size = New System.Drawing.Size(170, 170)
        Me.PictureBox_BoostOFF.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize
        Me.PictureBox_BoostOFF.TabIndex = 2
        Me.PictureBox_BoostOFF.TabStop = False
        Me.PictureBox_BoostOFF.Visible = False
        '
        'PictureBox_BoostON
        '
        Me.PictureBox_BoostON.BackColor = System.Drawing.Color.Transparent
        Me.PictureBox_BoostON.Image = CType(resources.GetObject("PictureBox_BoostON.Image"), System.Drawing.Image)
        Me.PictureBox_BoostON.Location = New System.Drawing.Point(44, 109)
        Me.PictureBox_BoostON.Name = "PictureBox_BoostON"
        Me.PictureBox_BoostON.Size = New System.Drawing.Size(170, 170)
        Me.PictureBox_BoostON.SizeMode = System.Windows.Forms.PictureBoxSizeMode.AutoSize
        Me.PictureBox_BoostON.TabIndex = 3
        Me.PictureBox_BoostON.TabStop = False
        Me.PictureBox_BoostON.Visible = False
        '
        'PictureBox_Online_ON
        '
        Me.PictureBox_Online_ON.BackColor = System.Drawing.Color.Transparent
        Me.PictureBox_Online_ON.Image = CType(resources.GetObject("PictureBox_Online_ON.Image"), System.Drawing.Image)
        Me.PictureBox_Online_ON.Location = New System.Drawing.Point(500, 320)
        Me.PictureBox_Online_ON.Name = "PictureBox_Online_ON"
        Me.PictureBox_Online_ON.Size = New System.Drawing.Size(50, 50)
        Me.PictureBox_Online_ON.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.PictureBox_Online_ON.TabIndex = 4
        Me.PictureBox_Online_ON.TabStop = False
        Me.PictureBox_Online_ON.Visible = False
        '
        'PictureBox_Online_OFF
        '
        Me.PictureBox_Online_OFF.BackColor = System.Drawing.Color.Transparent
        Me.PictureBox_Online_OFF.Image = CType(resources.GetObject("PictureBox_Online_OFF.Image"), System.Drawing.Image)
        Me.PictureBox_Online_OFF.Location = New System.Drawing.Point(500, 320)
        Me.PictureBox_Online_OFF.Name = "PictureBox_Online_OFF"
        Me.PictureBox_Online_OFF.Size = New System.Drawing.Size(50, 50)
        Me.PictureBox_Online_OFF.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage
        Me.PictureBox_Online_OFF.TabIndex = 5
        Me.PictureBox_Online_OFF.TabStop = False
        Me.PictureBox_Online_OFF.Visible = False
        '
        'NotifyIcon_Main
        '
        Me.NotifyIcon_Main.ContextMenuStrip = Me.ContextMenuStrip_Main
        Me.NotifyIcon_Main.Icon = CType(resources.GetObject("NotifyIcon_Main.Icon"), System.Drawing.Icon)
        Me.NotifyIcon_Main.Text = "NotifyIcon1"
        Me.NotifyIcon_Main.Visible = True
        '
        'ContextMenuStrip_Main
        '
        Me.ContextMenuStrip_Main.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.AboutMeToolStripMenuItem, Me.BoostOnToolStripMenuItem, Me.BoostOfToolStripMenuItem, Me.CloseToolStripMenuItem})
        Me.ContextMenuStrip_Main.Name = "ContextMenuStrip_Main"
        Me.ContextMenuStrip_Main.Size = New System.Drawing.Size(128, 92)
        '
        'AboutMeToolStripMenuItem
        '
        Me.AboutMeToolStripMenuItem.Name = "AboutMeToolStripMenuItem"
        Me.AboutMeToolStripMenuItem.Size = New System.Drawing.Size(127, 22)
        Me.AboutMeToolStripMenuItem.Text = "About Me"
        '
        'BoostOnToolStripMenuItem
        '
        Me.BoostOnToolStripMenuItem.Name = "BoostOnToolStripMenuItem"
        Me.BoostOnToolStripMenuItem.Size = New System.Drawing.Size(127, 22)
        Me.BoostOnToolStripMenuItem.Text = "Boost On"
        '
        'BoostOfToolStripMenuItem
        '
        Me.BoostOfToolStripMenuItem.Name = "BoostOfToolStripMenuItem"
        Me.BoostOfToolStripMenuItem.Size = New System.Drawing.Size(127, 22)
        Me.BoostOfToolStripMenuItem.Text = "Boost Off"
        '
        'CloseToolStripMenuItem
        '
        Me.CloseToolStripMenuItem.Name = "CloseToolStripMenuItem"
        Me.CloseToolStripMenuItem.Size = New System.Drawing.Size(127, 22)
        Me.CloseToolStripMenuItem.Text = "Close"
        '
        'Process1
        '
        Me.Process1.StartInfo.Domain = ""
        Me.Process1.StartInfo.LoadUserProfile = False
        Me.Process1.StartInfo.Password = Nothing
        Me.Process1.StartInfo.StandardErrorEncoding = Nothing
        Me.Process1.StartInfo.StandardOutputEncoding = Nothing
        Me.Process1.StartInfo.UserName = ""
        Me.Process1.SynchronizingObject = Me
        '
        'Form_Main
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch
        Me.ClientSize = New System.Drawing.Size(562, 382)
        Me.Controls.Add(Me.PictureBox_Online_OFF)
        Me.Controls.Add(Me.PictureBox_Online_ON)
        Me.Controls.Add(Me.PictureBox_Logo)
        Me.Controls.Add(Me.PictureBox_BoostOFF)
        Me.Controls.Add(Me.PictureBox_BoostON)
        Me.DoubleBuffered = True
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.MaximumSize = New System.Drawing.Size(578, 420)
        Me.MinimumSize = New System.Drawing.Size(578, 420)
        Me.Name = "Form_Main"
        Me.Text = "Game Booster"
        CType(Me.PictureBox_Logo, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox_BoostOFF, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox_BoostON, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox_Online_ON, System.ComponentModel.ISupportInitialize).EndInit()
        CType(Me.PictureBox_Online_OFF, System.ComponentModel.ISupportInitialize).EndInit()
        Me.ContextMenuStrip_Main.ResumeLayout(False)
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents PictureBox_Logo As System.Windows.Forms.PictureBox
    Friend WithEvents PictureBox_BoostOFF As System.Windows.Forms.PictureBox
    Friend WithEvents PictureBox_BoostON As System.Windows.Forms.PictureBox
    Friend WithEvents PictureBox_Online_ON As System.Windows.Forms.PictureBox
    Friend WithEvents PictureBox_Online_OFF As System.Windows.Forms.PictureBox
    Friend WithEvents NotifyIcon_Main As System.Windows.Forms.NotifyIcon
    Friend WithEvents ContextMenuStrip_Main As System.Windows.Forms.ContextMenuStrip
    Friend WithEvents AboutMeToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents BoostOnToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents BoostOfToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents CloseToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents Process1 As System.Diagnostics.Process
    Friend WithEvents ServiceController1 As System.ServiceProcess.ServiceController

End Class
