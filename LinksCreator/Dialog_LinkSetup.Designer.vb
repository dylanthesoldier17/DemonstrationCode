<Global.Microsoft.VisualBasic.CompilerServices.DesignerGenerated()> _
Partial Class Dialog_LinkSetup
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
        Me.TableLayoutPanel1 = New System.Windows.Forms.TableLayoutPanel()
        Me.OK_Button = New System.Windows.Forms.Button()
        Me.Cancel_Button = New System.Windows.Forms.Button()
        Me.Button__Dialog_Browse = New System.Windows.Forms.Button()
        Me.RadioButton_Sym_Folder = New System.Windows.Forms.RadioButton()
        Me.RadioButton_Sym_File = New System.Windows.Forms.RadioButton()
        Me.RadioButton_Hard_Link = New System.Windows.Forms.RadioButton()
        Me.RadioButton_Mount_Folder = New System.Windows.Forms.RadioButton()
        Me.TextBox_Dialog_Target = New System.Windows.Forms.TextBox()
        Me.GroupBox1 = New System.Windows.Forms.GroupBox()
        Me.Label1 = New System.Windows.Forms.Label()
        Me.Label2 = New System.Windows.Forms.Label()
        Me.TextBox_Dialog_Destination = New System.Windows.Forms.TextBox()
        Me.ComboBox_MountLetter = New System.Windows.Forms.ComboBox()
        Me.Label3 = New System.Windows.Forms.Label()
        Me.TextBox_FileName = New System.Windows.Forms.TextBox()
        Me.TableLayoutPanel1.SuspendLayout()
        Me.GroupBox1.SuspendLayout()
        Me.SuspendLayout()
        '
        'TableLayoutPanel1
        '
        Me.TableLayoutPanel1.Anchor = CType((System.Windows.Forms.AnchorStyles.Bottom Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.TableLayoutPanel1.ColumnCount = 2
        Me.TableLayoutPanel1.ColumnStyles.Add(New System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50.0!))
        Me.TableLayoutPanel1.ColumnStyles.Add(New System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 50.0!))
        Me.TableLayoutPanel1.Controls.Add(Me.OK_Button, 0, 0)
        Me.TableLayoutPanel1.Controls.Add(Me.Cancel_Button, 1, 0)
        Me.TableLayoutPanel1.Location = New System.Drawing.Point(322, 118)
        Me.TableLayoutPanel1.Name = "TableLayoutPanel1"
        Me.TableLayoutPanel1.RowCount = 1
        Me.TableLayoutPanel1.RowStyles.Add(New System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Percent, 50.0!))
        Me.TableLayoutPanel1.Size = New System.Drawing.Size(146, 29)
        Me.TableLayoutPanel1.TabIndex = 0
        '
        'OK_Button
        '
        Me.OK_Button.Anchor = System.Windows.Forms.AnchorStyles.None
        Me.OK_Button.Location = New System.Drawing.Point(3, 3)
        Me.OK_Button.Name = "OK_Button"
        Me.OK_Button.Size = New System.Drawing.Size(67, 23)
        Me.OK_Button.TabIndex = 0
        Me.OK_Button.Text = "OK"
        '
        'Cancel_Button
        '
        Me.Cancel_Button.Anchor = System.Windows.Forms.AnchorStyles.None
        Me.Cancel_Button.DialogResult = System.Windows.Forms.DialogResult.Cancel
        Me.Cancel_Button.Location = New System.Drawing.Point(76, 3)
        Me.Cancel_Button.Name = "Cancel_Button"
        Me.Cancel_Button.Size = New System.Drawing.Size(67, 23)
        Me.Cancel_Button.TabIndex = 1
        Me.Cancel_Button.Text = "Cancel"
        '
        'Button__Dialog_Browse
        '
        Me.Button__Dialog_Browse.Anchor = CType((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.Button__Dialog_Browse.Location = New System.Drawing.Point(386, 12)
        Me.Button__Dialog_Browse.Name = "Button__Dialog_Browse"
        Me.Button__Dialog_Browse.Size = New System.Drawing.Size(76, 23)
        Me.Button__Dialog_Browse.TabIndex = 1
        Me.Button__Dialog_Browse.Text = "Browse"
        Me.Button__Dialog_Browse.UseVisualStyleBackColor = True
        '
        'RadioButton_Sym_Folder
        '
        Me.RadioButton_Sym_Folder.AutoSize = True
        Me.RadioButton_Sym_Folder.Checked = True
        Me.RadioButton_Sym_Folder.Location = New System.Drawing.Point(15, 42)
        Me.RadioButton_Sym_Folder.Name = "RadioButton_Sym_Folder"
        Me.RadioButton_Sym_Folder.Size = New System.Drawing.Size(122, 17)
        Me.RadioButton_Sym_Folder.TabIndex = 2
        Me.RadioButton_Sym_Folder.TabStop = True
        Me.RadioButton_Sym_Folder.Text = "Symbolic Folder Link"
        Me.RadioButton_Sym_Folder.UseVisualStyleBackColor = True
        '
        'RadioButton_Sym_File
        '
        Me.RadioButton_Sym_File.AutoSize = True
        Me.RadioButton_Sym_File.Location = New System.Drawing.Point(15, 19)
        Me.RadioButton_Sym_File.Name = "RadioButton_Sym_File"
        Me.RadioButton_Sym_File.Size = New System.Drawing.Size(109, 17)
        Me.RadioButton_Sym_File.TabIndex = 3
        Me.RadioButton_Sym_File.Text = "Symbolic File Link"
        Me.RadioButton_Sym_File.UseVisualStyleBackColor = True
        '
        'RadioButton_Hard_Link
        '
        Me.RadioButton_Hard_Link.AutoSize = True
        Me.RadioButton_Hard_Link.Location = New System.Drawing.Point(154, 42)
        Me.RadioButton_Hard_Link.Name = "RadioButton_Hard_Link"
        Me.RadioButton_Hard_Link.Size = New System.Drawing.Size(71, 17)
        Me.RadioButton_Hard_Link.TabIndex = 4
        Me.RadioButton_Hard_Link.Text = "Hard Link"
        Me.RadioButton_Hard_Link.UseVisualStyleBackColor = True
        '
        'RadioButton_Mount_Folder
        '
        Me.RadioButton_Mount_Folder.AutoSize = True
        Me.RadioButton_Mount_Folder.Location = New System.Drawing.Point(154, 19)
        Me.RadioButton_Mount_Folder.Name = "RadioButton_Mount_Folder"
        Me.RadioButton_Mount_Folder.Size = New System.Drawing.Size(87, 17)
        Me.RadioButton_Mount_Folder.TabIndex = 5
        Me.RadioButton_Mount_Folder.Text = "Mount Folder"
        Me.RadioButton_Mount_Folder.UseVisualStyleBackColor = True
        '
        'TextBox_Dialog_Target
        '
        Me.TextBox_Dialog_Target.Anchor = CType(((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.TextBox_Dialog_Target.Location = New System.Drawing.Point(88, 14)
        Me.TextBox_Dialog_Target.Name = "TextBox_Dialog_Target"
        Me.TextBox_Dialog_Target.Size = New System.Drawing.Size(293, 20)
        Me.TextBox_Dialog_Target.TabIndex = 6
        '
        'GroupBox1
        '
        Me.GroupBox1.Controls.Add(Me.RadioButton_Mount_Folder)
        Me.GroupBox1.Controls.Add(Me.RadioButton_Sym_Folder)
        Me.GroupBox1.Controls.Add(Me.RadioButton_Sym_File)
        Me.GroupBox1.Controls.Add(Me.RadioButton_Hard_Link)
        Me.GroupBox1.Location = New System.Drawing.Point(15, 64)
        Me.GroupBox1.Name = "GroupBox1"
        Me.GroupBox1.Size = New System.Drawing.Size(253, 68)
        Me.GroupBox1.TabIndex = 7
        Me.GroupBox1.TabStop = False
        Me.GroupBox1.Text = "Options"
        '
        'Label1
        '
        Me.Label1.AutoSize = True
        Me.Label1.Location = New System.Drawing.Point(12, 17)
        Me.Label1.Name = "Label1"
        Me.Label1.Size = New System.Drawing.Size(38, 13)
        Me.Label1.TabIndex = 8
        Me.Label1.Text = "Target"
        '
        'Label2
        '
        Me.Label2.AutoSize = True
        Me.Label2.Location = New System.Drawing.Point(12, 41)
        Me.Label2.Name = "Label2"
        Me.Label2.Size = New System.Drawing.Size(60, 13)
        Me.Label2.TabIndex = 9
        Me.Label2.Text = "Destination"
        '
        'TextBox_Dialog_Destination
        '
        Me.TextBox_Dialog_Destination.Anchor = CType(((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Left) _
                    Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.TextBox_Dialog_Destination.Location = New System.Drawing.Point(88, 38)
        Me.TextBox_Dialog_Destination.Name = "TextBox_Dialog_Destination"
        Me.TextBox_Dialog_Destination.Size = New System.Drawing.Size(293, 20)
        Me.TextBox_Dialog_Destination.TabIndex = 10
        '
        'ComboBox_MountLetter
        '
        Me.ComboBox_MountLetter.Anchor = CType((System.Windows.Forms.AnchorStyles.Top Or System.Windows.Forms.AnchorStyles.Right), System.Windows.Forms.AnchorStyles)
        Me.ComboBox_MountLetter.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList
        Me.ComboBox_MountLetter.Enabled = False
        Me.ComboBox_MountLetter.FlatStyle = System.Windows.Forms.FlatStyle.Flat
        Me.ComboBox_MountLetter.FormattingEnabled = True
        Me.ComboBox_MountLetter.Items.AddRange(New Object() {"A", "B", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "C", "D"})
        Me.ComboBox_MountLetter.Location = New System.Drawing.Point(421, 41)
        Me.ComboBox_MountLetter.Name = "ComboBox_MountLetter"
        Me.ComboBox_MountLetter.Size = New System.Drawing.Size(40, 21)
        Me.ComboBox_MountLetter.TabIndex = 11
        '
        'Label3
        '
        Me.Label3.AutoSize = True
        Me.Label3.Location = New System.Drawing.Point(298, 71)
        Me.Label3.Name = "Label3"
        Me.Label3.Size = New System.Drawing.Size(132, 13)
        Me.Label3.TabIndex = 12
        Me.Label3.Text = "File Name / Folder's Name"
        '
        'TextBox_FileName
        '
        Me.TextBox_FileName.Location = New System.Drawing.Point(274, 87)
        Me.TextBox_FileName.Name = "TextBox_FileName"
        Me.TextBox_FileName.Size = New System.Drawing.Size(184, 20)
        Me.TextBox_FileName.TabIndex = 13
        '
        'Dialog_LinkSetup
        '
        Me.AcceptButton = Me.OK_Button
        Me.AutoScaleDimensions = New System.Drawing.SizeF(6.0!, 13.0!)
        Me.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font
        Me.CancelButton = Me.Cancel_Button
        Me.ClientSize = New System.Drawing.Size(470, 147)
        Me.Controls.Add(Me.TextBox_FileName)
        Me.Controls.Add(Me.Label3)
        Me.Controls.Add(Me.ComboBox_MountLetter)
        Me.Controls.Add(Me.TextBox_Dialog_Destination)
        Me.Controls.Add(Me.Label2)
        Me.Controls.Add(Me.Label1)
        Me.Controls.Add(Me.GroupBox1)
        Me.Controls.Add(Me.TextBox_Dialog_Target)
        Me.Controls.Add(Me.Button__Dialog_Browse)
        Me.Controls.Add(Me.TableLayoutPanel1)
        Me.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedDialog
        Me.MaximizeBox = False
        Me.MinimizeBox = False
        Me.MinimumSize = New System.Drawing.Size(455, 175)
        Me.Name = "Dialog_LinkSetup"
        Me.ShowInTaskbar = False
        Me.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent
        Me.Text = "Creating Link"
        Me.TableLayoutPanel1.ResumeLayout(False)
        Me.GroupBox1.ResumeLayout(False)
        Me.GroupBox1.PerformLayout()
        Me.ResumeLayout(False)
        Me.PerformLayout()

    End Sub
    Friend WithEvents TableLayoutPanel1 As System.Windows.Forms.TableLayoutPanel
    Friend WithEvents OK_Button As System.Windows.Forms.Button
    Friend WithEvents Cancel_Button As System.Windows.Forms.Button
    Friend WithEvents Button__Dialog_Browse As System.Windows.Forms.Button
    Friend WithEvents RadioButton_Sym_Folder As System.Windows.Forms.RadioButton
    Friend WithEvents RadioButton_Sym_File As System.Windows.Forms.RadioButton
    Friend WithEvents RadioButton_Hard_Link As System.Windows.Forms.RadioButton
    Friend WithEvents RadioButton_Mount_Folder As System.Windows.Forms.RadioButton
    Friend WithEvents TextBox_Dialog_Target As System.Windows.Forms.TextBox
    Friend WithEvents GroupBox1 As System.Windows.Forms.GroupBox
    Friend WithEvents Label1 As System.Windows.Forms.Label
    Friend WithEvents Label2 As System.Windows.Forms.Label
    Friend WithEvents TextBox_Dialog_Destination As System.Windows.Forms.TextBox
    Friend WithEvents ComboBox_MountLetter As System.Windows.Forms.ComboBox
    Friend WithEvents Label3 As System.Windows.Forms.Label
    Friend WithEvents TextBox_FileName As System.Windows.Forms.TextBox

End Class
