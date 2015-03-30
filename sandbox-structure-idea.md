Sandbox Structure Idea
======================

In this document, "root" is an arbitrary folder where the program is stored, like "/User/Username/Library/" or
"C:/Program Files/".
Host Server contains archives of all versions of the program, or at least data about how to update from any old version
to any new version.
The example program in this document will be "My BARCing Program".

+-------------------------------------------+
| Root                                      |
| +---------------------------------------+ |
| | Program                               | |
| | +---------+---------+--------- -  -   | |
| | | Sandbox | Sandbox | Sandbox...      | |
| | +---------+---------+--------- -  -   | |
| +---------------------------------------+ |
+-------------------------------------------+

root/My BARCing Program/        This is the Program. It contains no special files that The BARC uses.
root/My BARCing Program/1.2.15/ This is the current Sandbox. It contains all files needed to run My BARCing Program version 1.2.15.
root/My BARCing Program/2.0.0/  This is the next Sandbox, which was just cloned from the current Sandbox and then had updates downloaded from the Host Server. It contains all files needed to run My BARCing Program version 2.0.0.