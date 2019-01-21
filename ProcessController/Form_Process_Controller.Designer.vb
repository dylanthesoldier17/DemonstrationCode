<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Form_Process_Controller
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
        Dim resources As System.ComponentModel.ComponentResourceManager = New System.ComponentModel.ComponentResourceManager(GetType(Form_Process_Controller))
        Me.ListBox_Boosted_Apps = New System.Windows.Forms.ListBox()
        Me.ContextMenuStrip_Listbox = New System.Windows.Forms.ContextMenuStrip(Me.components)
        Me.AddToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.RemoveToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ClearToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.Button_Boost_OFF = New System.Windows.Forms.Button()
        Me.Button_Boost_ON = New System.Windows.Forms.Button()
        Me.OpenFileDialog_Main = New System.Windows.Forms.OpenFileDialog()
        Me.Timer_Main = New System.Windows.Forms.Timer(Me.components)
        Me.NotifyIcon_Main = New System.Windows.Forms.NotifyIcon(Me.components)
        Me.ContextMenuStrip_NotifyIcon = New System.Windows.Forms.ContextMenuStrip(Me.components)
        Me.AboutMeToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.BoostOnToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.BoostOfToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.CloseToolStripMenuItem = New System.Windows.Forms.ToolStripMenuItem()
        Me.ContextMenuStrip_Listbox.SuspendLayout()
        Me.ContextMenuStrip_NotifyIcon.SuspendLayout()
        Me.SuspendLayout()
        '
        'ListBox_Boosted_Apps
        '
        Me.ListBox_Boosted_Apps.Anchor = CType((((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Bottom) _
                    Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.ListBox_Boosted_Apps.ContextMenuStrip = Me.ContextMenuStrip_Listbox
        Me.ListBox_Boosted_Apps.FormattingEnabled = True
        Me.ListBox_Boosted_Apps.Location = New System.Drawing.Point(12, 12)
        Me.ListBox_Boosted_Apps.Name = "ListBox_Boosted_Apps"
        Me.ListBox_Boosted_Apps.Size = New System.Drawing.Size(288, 199)
        Me.ListBox_Boosted_Apps.TabIndex = 0
        '
        'ContextMenuStrip_Listbox
        '
        Me.ContextMenuStrip_Listbox.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.AddToolStripMenuItem, Me.RemoveToolStripMenuItem, Me.ClearToolStripMenuItem})
        Me.ContextMenuStrip_Listbox.Name = "ContextMenuStrip1"
        Me.ContextMenuStrip_Listbox.Size = New System.Drawing.Size(118, 70)
        '
        'AddToolStripMenuItem
        '
        Me.AddToolStripMenuItem.Name = "AddToolStripMenuItem"
        Me.AddToolStripMenuItem.Size = New System.Drawing.Size(117, 22)
        Me.AddToolStripMenuItem.Text = "Add"
        '
        'RemoveToolStripMenuItem
        '
        Me.RemoveToolStripMenuItem.Name = "RemoveToolStripMenuItem"
        Me.RemoveToolStripMenuItem.Size = New System.Drawing.Size(117, 22)
        Me.RemoveToolStripMenuItem.Text = "Remove"
        '
        'ClearToolStripMenuItem
        '
        Me.ClearToolStripMenuItem.Name = "ClearToolStripMenuItem"
        Me.ClearToolStripMenuItem.Size = New System.Drawing.Size(117, 22)
        Me.ClearToolStripMenuItem.Text = "Clear"
        '
        'Button_Boost_OFF
        '
        Me.Button_Boost_OFF.Anchor = CType((System.Windows.Forms.AnchorStyles.Bottom Or System.Windows.Forms.AnchorStyles.Left), System.Windows.Forms.AnchorStyles)
        Me.Button_Boost_OFF.Location = New System.Drawing.Point(12, 221)
        Me.Button_Boost_OFF.Name = "Button_Boost_OFF"
        Me.Button_Boost_OFF.Size = New System.Drawing.Size(75, 23)
        Me.Button_Boost_OFF.TabIndex = 1
        Me.Button_Boost_OFF.Text = "Boost OFF"
        Me.Button_Boost_OFF.UseVisualStyleBackColor = True
        '
        'Button_Boost_ON
        '
        Me.Button_Boost_ON.Anchor = CType((System.Windows.Forms.AnchorStyles.Bottom Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.Button_Boost_ON.Location = New System.Drawing.Point(225, 221)
        Me.Button_Boost_ON.Name = "Button_Boost_ON"
        Me.Button_Boost_ON.Size = New System.Drawing.Size(75, 23)
        Me.Button_Boost_ON.TabIndex = 2
        Me.Button_Boost_ON.Text = "Boost ON"
        Me.Button_Boost_ON.UseVisualStyleBackColor = True
        '
        'OpenFileDialog_Main
        '
        Me.OpenFileDialog_Main.FileName = "Your App.exe"
        Me.OpenFileDialog_Main.Title = "Choose the application to Boost!"
        '
        'Timer_Main
        '
        Me.Timer_Main.Interval = 2500
        '
        'NotifyIcon_Main
        '
        Me.NotifyIcon_Main.ContextMenuStrip = Me.ContextMenuStrip_NotifyIcon
        Me.NotifyIcon_Main.Icon = CType(resources.GetObject("NotifyIcon_Main.Icon"), System.Drawing.Icon)
        Me.NotifyIcon_Main.Text = "Process Controller"
        Me.NotifyIcon_Main.Visible = True
        '
        'ContextMenuStrip_NotifyIcon
        '
        Me.ContextMenuStrip_NotifyIcon.Items.AddRange(New System.Windows.Forms.ToolStripItem() {Me.AboutMeToolStripMenuItem, Me.BoostOnToolStripMenuItem, Me.BoostOfToolStripMenuItem, Me.CloseToolStripMenuItem})
        Me.ContextMenuStrip_NotifyIcon.Name = "ContextMenuStrip_NotifyIcon"
        Me.ContextMenuStrip_NotifyIcon.Size = New System.Drawing.Size(128, 92)
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
        Me.BoostOfToolStripMenuItem.Text = "Boost OfF"
        '
        'CloseToolStripMenuItem
        '
        Me.CloseToolStripMenuItem.Name = "CloseToolStripMenuItem"
        Me.CloseToolStripMenuItem.Size = New System.Drawing.Size(127, 22)
        Me.CloseToolStripMenuItem.Text = "Close"
        '
        'Form_Process_Controller
        '
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.ClientSize = New System.Drawing.Size(312, 253)
        Me.Controls.Add(Me.Button_Boost_OFF)
        Me.Controls.Add(Me.Button_Boost_ON)
        Me.Controls.Add(Me.ListBox_Boosted_Apps)
        Me.Icon = CType(resources.GetObject("$this.Icon"), System.Drawing.Icon)
        Me.Name = "Form_Process_Controller"
        Me.Text = "Process Controller"
        Me.ContextMenuStrip_Listbox.ResumeLayout(False)
        Me.ContextMenuStrip_NotifyIcon.ResumeLayout(False)
        Me.ResumeLayout(False)

    End Sub
    Friend WithEvents ListBox_Boosted_Apps As System.Windows.Forms.ListBox
    Friend WithEvents Button_Boost_OFF As System.Windows.Forms.Button
    Friend WithEvents Button_Boost_ON As System.Windows.Forms.Button
    Friend WithEvents ContextMenuStrip_Listbox As System.Windows.Forms.ContextMenuStrip
    Friend WithEvents AddToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents RemoveToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents ClearToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents OpenFileDialog_Main As System.Windows.Forms.OpenFileDialog
    Friend WithEvents Timer_Main As System.Windows.Forms.Timer
    Friend WithEvents NotifyIcon_Main As System.Windows.Forms.NotifyIcon
    Friend WithEvents ContextMenuStrip_NotifyIcon As System.Windows.Forms.ContextMenuStrip
    Friend WithEvents AboutMeToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents BoostOnToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents BoostOfToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem
    Friend WithEvents CloseToolStripMenuItem As System.Windows.Forms.ToolStripMenuItem

End Class
