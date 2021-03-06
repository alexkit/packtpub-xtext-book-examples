/**
 * generated by Xtext
 */
package org.example.entities.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Arrays;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;
import org.example.entities.entities.Attribute;
import org.example.entities.entities.AttributeType;
import org.example.entities.entities.BasicType;
import org.example.entities.entities.ElementType;
import org.example.entities.entities.Entity;
import org.example.entities.entities.EntityType;

@SuppressWarnings("all")
public class EntitiesGenerator implements IGenerator {
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<Entity> _filter = Iterables.<Entity>filter(_iterable, Entity.class);
    final Procedure1<Entity> _function = new Procedure1<Entity>() {
      public void apply(final Entity it) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append("entities/");
        String _name = it.getName();
        _builder.append(_name, "");
        _builder.append(".java");
        CharSequence _compile = EntitiesGenerator.this.compile(it);
        fsa.generateFile(_builder.toString(), _compile);
      }
    };
    IterableExtensions.<Entity>forEach(_filter, _function);
  }
  
  public CharSequence compile(final Entity entity) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package entities;");
    _builder.newLine();
    _builder.newLine();
    _builder.append("public class ");
    String _name = entity.getName();
    _builder.append(_name, "");
    _builder.append(" ");
    {
      Entity _superType = entity.getSuperType();
      boolean _notEquals = (!Objects.equal(_superType, null));
      if (_notEquals) {
        _builder.append("extends ");
        Entity _superType_1 = entity.getSuperType();
        String _name_1 = _superType_1.getName();
        _builder.append(_name_1, "");
        _builder.append(" ");
      }
    }
    _builder.append("{");
    _builder.newLineIfNotEmpty();
    {
      EList<Attribute> _attributes = entity.getAttributes();
      for(final Attribute attribute : _attributes) {
        _builder.append("\t");
        _builder.append("private ");
        AttributeType _type = attribute.getType();
        String _compile = this.compile(_type);
        _builder.append(_compile, "\t");
        _builder.append(" ");
        String _name_2 = attribute.getName();
        _builder.append(_name_2, "\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.newLine();
    {
      EList<Attribute> _attributes_1 = entity.getAttributes();
      for(final Attribute attribute_1 : _attributes_1) {
        _builder.append("\t");
        _builder.append("public ");
        AttributeType _type_1 = attribute_1.getType();
        String _compile_1 = this.compile(_type_1);
        _builder.append(_compile_1, "\t");
        _builder.append(" get");
        String _name_3 = attribute_1.getName();
        String _firstUpper = StringExtensions.toFirstUpper(_name_3);
        _builder.append(_firstUpper, "\t");
        _builder.append("() {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("return ");
        String _name_4 = attribute_1.getName();
        _builder.append(_name_4, "\t\t");
        _builder.append(";");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
        _builder.append("\t");
        _builder.append("public void set");
        String _name_5 = attribute_1.getName();
        String _firstUpper_1 = StringExtensions.toFirstUpper(_name_5);
        _builder.append(_firstUpper_1, "\t");
        _builder.append("(");
        AttributeType _type_2 = attribute_1.getType();
        String _compile_2 = this.compile(_type_2);
        _builder.append(_compile_2, "\t");
        _builder.append(" _arg) {");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("\t");
        _builder.append("this.");
        String _name_6 = attribute_1.getName();
        _builder.append(_name_6, "\t\t");
        _builder.append(" = _arg;");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("}");
        _builder.newLine();
        _builder.append("\t");
        _builder.newLine();
      }
    }
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public String compile(final AttributeType attributeType) {
    ElementType _elementType = attributeType.getElementType();
    String _typeToString = this.typeToString(_elementType);
    String _xifexpression = null;
    boolean _isArray = attributeType.isArray();
    if (_isArray) {
      _xifexpression = "[]";
    } else {
      _xifexpression = "";
    }
    return (_typeToString + _xifexpression);
  }
  
  protected String _typeToString(final BasicType type) {
    String _xifexpression = null;
    String _typeName = type.getTypeName();
    boolean _equals = Objects.equal(_typeName, "string");
    if (_equals) {
      _xifexpression = "String";
    } else {
      _xifexpression = type.getTypeName();
    }
    return _xifexpression;
  }
  
  protected String _typeToString(final EntityType type) {
    Entity _entity = type.getEntity();
    return _entity.getName();
  }
  
  public String typeToString(final ElementType type) {
    if (type instanceof BasicType) {
      return _typeToString((BasicType)type);
    } else if (type instanceof EntityType) {
      return _typeToString((EntityType)type);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(type).toString());
    }
  }
}
