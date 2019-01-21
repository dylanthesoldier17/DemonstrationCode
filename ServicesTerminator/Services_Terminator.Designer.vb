<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Services_Terminator
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(Services_Terminator))
        Me.RadioButton_OfflineMode = New System.Windows.Forms.RadioButton()
        Me.RadioButton_OnlineMode = New System.Windows.Forms.RadioButton()
        Me.ContextMenuStrip_Main = New System.Windows.Forms.ContextMenuStrip(Me.components)
        Me.ChangeServicesToolStripMenuItem_Change = New System.Windows.Forms.ToolStripMenuItem()
        Me.Button_Restart = New System.Windows.Forms.Button()
        Me.Button_Stop = New System.Windows.Forms.Button()
        Me.ServiceController_Main = New System.ServiceProcess.ServiceController()
        Me.GroupBox_Options = New System.Windows.Forms.GroupBox()
        Me.NotifyIcon_Main = New System.Windows.Forms.NotifyIcon(Me.components)
        Me.ContextMenuStrip_NotifyIcon = New System.Windows.Forms.ContextMenuStrip(Me.components)
        Me.ShowToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ChangeServicesToolStripMenuItem1 = New System.Windows.Forms.ToolStripMenuItem()
        Me.AboutMeToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.CloseToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.StopToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.RestartToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ToolStripSeparator1 = New System.Windows.Forms.ToolStripSeparator()
        Me.ToolStripSeparator2 = New System.Windows.Forms.ToolStripSeparator()
        Me.ContextMenuStrip_Main.SuspendLayout()
        Me.GroupBox_Options.SuspendLayout()
        Me.ContextMenuStrip_NotifyIcon.SuspendLayout()
        Me.SuspendLayout()
        '
        'RadioButton_OfflineMode
        '
        Me.RadioButton_OfflineMode.AutoSize = True
        Me.RadioButton_OfflineMode.Font = New System.Drawing.Font("Courier New", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.RadioButton_OfflineMode.Location = New System.Drawing.Point(15, 19)
        Me.RadioButton_OfflineMode.Name = "RadioButton_OfflineMode"
        Me.RadioButton_OfflineMode.Size = New System.Drawing.Size(109, 18)
        Me.RadioButton_OfflineMode.TabIndex = 0
        Me.RadioButton_OfflineMode.Text = "Offline Mode"
        Me.RadioButton_OfflineMode.UseVisualStyleBackColor = True
        '
        'RadioButton_OnlineMode
        '
        Me.RadioButton_OnlineMode.AutoSize = True
        Me.RadioButton_OnlineMode.Checked = True
        Me.RadioButton_OnlineMode.Font = New System.Drawing.Font("Courier New", 8.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.RadioButton_OnlineMode.Location = New System.Drawing.Point(140, 19)
        Me.RadioButton_OnlineMode.Name = "RadioButton_OnlineMode"
        Me.RadioButton_OnlineMode.Size = New System.Drawing.Size(102, 18)
        Me.RadioButton_OnlineMode.TabIndex = 1
        Me.RadioButton_OnlineMode.TabStop = True
        Me.RadioButton_OnlineMode.Text = "Online Mode"
        Me.RadioButton_OnlineMode.UseVisualStyleBackColor = True
        '
        'ContextMenuStrip_Main
        '
        Me.ContextMenuStrip_Main.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.ChangeServicesToolStripMenuItem_Change})
        Me.ContextMenuStrip_Main.Name = "ContextMenuStrip_Main"
        Me.ContextMenuStrip_Main.Size = New System.Drawing.Size(161, 26)
        '
        'ChangeServicesToolStripMenuItem_Change
        '
        Me.ChangeServicesToolStripMenuItem_Change.Name = "ChangeServicesToolStripMenuItem_Change"
        Me.ChangeServicesToolStripMenuItem_Change.Size = New System.Drawing.Size(160, 22)
        Me.ChangeServicesToolStripMenuItem_Change.Text = "Change Services"
        '
        'Button_Restart
        '
        Me.Button_Restart.BackColor = System.Drawing.Color.Transparent
        Me.Button_Restart.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.Button_Restart.ForeColor = System.Drawing.Color.Cyan
        Me.Button_Restart.Location = New System.Drawing.Point(12, 65)
        Me.Button_Restart.Name = "Button_Restart"
        Me.Button_Restart.Size = New System.Drawing.Size(75, 23)
        Me.Button_Restart.TabIndex = 3
        Me.Button_Restart.Text = "Restart"
        Me.Button_Restart.UseVisualStyleBackColor = False
        '
        'Button_Stop
        '
        Me.Button_Stop.BackColor = System.Drawing.Color.Transparent
        Me.Button_Stop.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.Button_Stop.ForeColor = System.Drawing.Color.Cyan
        Me.Button_Stop.Location = New System.Drawing.Point(197, 67)
        Me.Button_Stop.Name = "Button_Stop"
        Me.Button_Stop.Size = New System.Drawing.Size(75, 23)
        Me.Button_Stop.TabIndex = 4
        Me.Button_Stop.Text = "Stop"
        Me.Button_Stop.UseVisualStyleBackColor = False
        '
        'GroupBox_Options
        '
        Me.GroupBox_Options.BackColor = System.Drawing.Color.Transparent
        Me.GroupBox_Options.Controls.Add(Me.RadioButton_OnlineMode)
        Me.GroupBox_Options.Controls.Add(Me.RadioButton_OfflineMode)
        Me.GroupBox_Options.Font = New System.Drawing.Font("Courier New", 11.25!, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, CType(0, Byte))
        Me.GroupBox_Options.ForeColor = System.Drawing.Color.Cyan
        Me.GroupBox_Options.Location = New System.Drawing.Point(12, 8)
        Me.GroupBox_Options.Name = "GroupBox_Options"
        Me.GroupBox_Options.Size = New System.Drawing.Size(260, 47)
        Me.GroupBox_Options.TabIndex = 5
        Me.GroupBox_Options.TabStop = False
        Me.GroupBox_Options.Text = "Options"
        '
        'NotifyIcon_Main
        '
        Me.NotifyIcon_Main.ContextMenuStrip = Me.ContextMenuStrip_NotifyIcon
        Me.NotifyIcon_Main.Icon = CType(resources.GetObject("NotifyIcon_Main.Icon"), System.Drawing.Icon)
        Me.NotifyIcon_Main.Text = "Services Terminator"
        Me.NotifyIcon_Main.Visible = True
        '
        'ContextMenuStrip_NotifyIcon
        '
        Me.ContextMenuStrip_NotifyIcon.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.ShowToolStripMenuItem, Me.ChangeServicesToolStripMenuItem1, Me.ToolStripSeparator1, Me.StopToolStripMenuItem, Me.RestartToolStripMenuItem, Me.ToolStripSeparator2, Me.AboutMeToolStripMenuItem, Me.CloseToolStripMenuItem})
        Me.ContextMenuStrip_NotifyIcon.Name = "ContextMenuStrip_NotifyIcon"
        Me.ContextMenuStrip_NotifyIcon.Size = New System.Drawing.Size(161, 148)
        '
        'ShowToolStripMenuItem
        '
        Me.ShowToolStripMenuItem.Name = "ShowToolStripMenuItem"
        Me.ShowToolStripMenuItem.Size = New System.Drawing.Size(160, 22)
        Me.ShowToolStripMenuItem.Text = "Show"
        '
        'ChangeServicesToolStripMenuItem1
        '
        Me.ChangeServicesToolStripMenuItem1.Name = "ChangeServicesToolStripMenuItem1"
        Me.ChangeServicesToolStripMenuItem1.Size = New System.Drawing.Size(160, 22)
        Me.ChangeServicesToolStripMenuItem1.Text = "Change Services"
        '
        'AboutMeToolStripMenuItem
        '
        Me.AboutMeToolStripMenuItem.Name = "AboutMeToolStripMenuItem"
        Me.AboutMeToolStripMenuItem.Size = New System.Drawing.Size(160, 22)
        Me.AboutMeToolStripMenuItem.Text = "About Me"
        '
        'CloseToolStripMenuItem
        '
        Me.CloseToolStripMenuItem.Name = "CloseToolStripMenuItem"
        Me.CloseToolStripMenuItem.Size = New System.Drawing.Size(160, 22)
        Me.CloseToolStripMenuItem.Text = "Close"
        '
        'StopToolStripMenuItem
        '
        Me.StopToolStripMenuItem.Name = "StopToolStripMenuItem"
        Me.StopToolStripMenuItem.Size = New System.Drawing.Size(160, 22)
        Me.StopToolStripMenuItem.Text = "Stop"
        '
        'RestartToolStripMenuItem
        '
        Me.RestartToolStripMenuItem.Name = "RestartToolStripMenuItem"
        Me.RestartToolStripMenuItem.Size = New System.Drawing.Size(160, 22)
        Me.RestartToolStripMenuItem.Text = "Restart"
        '
        'ToolStripSeparator1
        '
        Me.ToolStripSeparator1.Name = "ToolStripSeparator1"
        Me.ToolStripSeparator1.Size = New System.Drawing.Size(157, 6)
        '
        'ToolStripSeparator2
        '
        Me.ToolStripSeparator2.Name = "ToolStripSeparator2"
        Me.ToolStripSeparator2.Size = New System.Drawing.Size(157, 6)
        '
        'Form_Main
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.BackgroundImage = CType(resources.GetObject("$this.BackgroundImage"), System.Drawing.Image)
        Me.ClientSize = New System.Drawing.Size(284, 102)
        Me.ContextMenuStrip = Me.ContextMenuStrip_Main
        Me.Controls.Add(Me.Button_Restart)
        Me.Controls.Add(Me.Button_Stop)
        Me.Controls.Add(Me.GroupBox_Options)
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.Name = "Form_Main"
        Me.Text = "Services Terminator"
        Me.ContextMenuStrip_Main.ResumeLayout(False)
        Me.GroupBox_Options.ResumeLayout(False)
        Me.GroupBox_Options.PerformLayout()
        Me.ContextMenuStrip_NotifyIcon.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents RadioButton_OfflineMode As System.Windows.Forms.RadioButton
    Friend WithEvents RadioButton_OnlineMode As System.Windows.Forms.RadioButton
    Friend WithEvents ContextMenuStrip_Main As System.Windows.Forms.ContextMenuStrip
    Friend WithEvents ChangeServicesToolStripMenuItem_Change As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents Button_Restart As System.Windows.Forms.Button
    Friend WithEvents Button_Stop As System.Windows.Forms.Button
    Friend WithEvents ServiceController_Main As System.ServiceProcess.ServiceController
    Friend WithEvents GroupBox_Options As System.Windows.Forms.GroupBox
    Friend WithEvents NotifyIcon_Main As System.Windows.Forms.NotifyIcon
    Friend WithEvents ContextMenuStrip_NotifyIcon As System.Windows.Forms.ContextMenuStrip
    Friend WithEvents ShowToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ChangeServicesToolStripMenuItem1 As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents CloseToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents AboutMeToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ToolStripSeparator1 As System.Windows.Forms.ToolStripSeparator
    Friend WithEvents StopToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents RestartToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ToolStripSeparator2 As System.Windows.Forms.ToolStripSeparator

End Class
